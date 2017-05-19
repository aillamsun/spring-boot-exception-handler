# spring-boot-expection-handler


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

## Exception Handler

```java
    @ExceptionHandler(value = GlobalErrorInfoException.class)
    public ResultBody errorHandlerOverJson(HttpServletRequest request, GlobalErrorInfoException exception) {
        ErrorInfo errorInfo = exception.getErrorInfo();
        ResultBody result = new ResultBody(errorInfo);
        return result;
    }
```
-----


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
> * Result:{"code":"000001","message":"params not null","result":null}