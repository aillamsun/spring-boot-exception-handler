# spring-boot-expection-handler

### 统一异常处理 构建 ResultBody 相应体

### 结合i18n 支持通配符匹配

## 使用实例 Contrller

```java
     /**
     * 获取 用户接口
     * @param account
     * @return
     * @throws GlobalErrorInfoException
     */
    @GetMapping
    public ResultBody findUsers(String account) throws GlobalErrorInfoException {
        // 入参为空
        if (StringUtils.isEmpty(account)) {
            throw new GlobalErrorInfoException(ErrorInfoEnum.PARAMS_NOT_NULL);
        }
        return new ResultBody(new User(1L, account, "admin", 18, 1));
    }
```

```java
     /**
         * 测试支持通配符匹配
         * @param account
         * @return
         * @throws GlobalErrorInfoException
         */
        @GetMapping("args")
        public ResultBody findUsers3(String account) throws GlobalErrorInfoException {
            // 入参为空
            if (StringUtils.isEmpty(account)) {
                Object[] args = new Object[]{"啊啊啊啊", "哈哈哈哈哈"};
                throw new GlobalErrorInfoException(ErrorInfoEnum.PARAMS_NOT_NULL2, args);
            }
            return new ResultBody(new User(1L, account, "admin", 18, 1));
        }
```

```java
    /**
     * 测试全局异常
     * @param account
     * @return
     */
    @GetMapping("other")
    public ResultBody otherExpetion(String account) {
        int i = 1 / 0;
        return new ResultBody(new User(1L, account, "admin", 18, 1));
    }
```


## Exception Handler

```java
@RestControllerAdvice
public class GlobalErrorInfoHandler {


private static Logger logger = LoggerFactory.getLogger(GlobalErrorInfoHandler.class);

     /**
         * 全局系统异常
         * @param request
         * @param exception
         * @return
         */
        @ExceptionHandler(value = RuntimeException.class)
        public ResultBody errorHandlerOverJson(HttpServletRequest request, RuntimeException exception) {
            logger.error("全局异常:", exception.getMessage());
            ResultBody result = new ResultBody(GlobalErrorInfoEnum.NOT_FOUND);
            return result;
        }
        
    
         /**
             * GlobalErrorInfoException 系统异常
             * @param request
             * @param exception
             * @return
             */
    @ExceptionHandler(value = GlobalErrorInfoException.class)
    public ResultBody errorHandlerOverJson(HttpServletRequest request, GlobalErrorInfoException exception) {
        ErrorInfo errorInfo = exception.getErrorInfo();
        getMessage(errorInfo,exception.getArgs());
        ResultBody result = new ResultBody(errorInfo);
        return result;
    }


    private void getMessage(ErrorInfo errorInfo,Object...agrs) {
        String message = null;
        if (!StringUtils.isEmpty(errorInfo.getCode())) {
            message = MessageUtils.message(errorInfo.getCode(), agrs);
        }
        if (message == null) {
            message = errorInfo.getMessage();
        }
        errorInfo.setMessage(message);
    }
}
```
-----

## 配置文件
```properties

000001=params not null in properties
000002=params not null {0} in properties {1}

```


## 测试 DEMO

```java
    @Test
    public void testSuccessGetUser() throws Exception {
        String uri = "/api/user?account=admin";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();

        System.out.println(status);
        System.out.println(content);
    }
```
> * Result:{"code":"200","message":"success","result":{"id":1,"account":"admin","name":"admin","age":18,"sex":1}}


```java

    /**
     * 测试i18n
     * @throws Exception
     */
    @Test
    public void testErrorGetUser() throws Exception {
        String uri = "/api/user";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(status);
        System.out.println(content);
    }
```
> * Result:{"code":"000001","message":"params not null in properties","result":null}



```java
    /**
     * 测试i18n 通配符
     * @throws Exception
     */
    @Test
       public void testErrorGetUserargs() throws Exception {
           String uri = "/api/user/args";
           MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();
           int status = mvcResult.getResponse().getStatus();
           String content = mvcResult.getResponse().getContentAsString();
           System.out.println(status);
           System.out.println(content);
       }
   }
```
> * Result:{"code":"000002","message":"params not null 啊啊啊啊 in properties 哈哈哈哈哈","result":null}

```java
 /**
     * 测试全局异常
     * @throws Exception
     */
    @Test
    public void testOther() throws Exception {
        String uri = "/api/user/other";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(status);
        System.out.println(content);
    }
```
> * Result:{"code":"00000","message":"global service error!","result":null}