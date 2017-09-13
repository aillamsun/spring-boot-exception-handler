package com.sung;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * Created by sungang on 2017/5/19.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringBootExceptionHandlerApplication.class)
@WebMvcTest
public class ErrorJsonControllerTest {

    @Autowired
    private MockMvc mvc;


    @Test
    public void testSuccessGetUser() throws Exception {
        String uri = "/api/user?account=admin";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();

        System.out.println(status);
        System.out.println(content);

    }

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
}
