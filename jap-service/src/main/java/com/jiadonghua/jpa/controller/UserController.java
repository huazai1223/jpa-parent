package com.jiadonghua.jpa.controller;

import com.jiadonghua.jpa.entity.*;
import com.jiadonghua.jpa.repository.UserRepository;
import com.jiadonghua.jpa.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
@Api(value = "没有什么意义",tags = {"用户","管理"})
@RestController
@RequestMapping("user")
@Slf4j
@CrossOrigin
public class UserController {

    @Autowired
    UserRepository userRespository;

    @Autowired
    UserService userService;

    @RequestMapping("test")
    public String user(){
        return "this is a test";
    }

    @RequestMapping("testadd")
    public String testadd(){
        User user = new User();
        user.setUsername("jdh");
        user.setPassword("hahah");
        user.setName("哈哈");
        user.setBirthday(new Date());
        user.setEmail("haha@qq.com");
        user.setTelephone("12348465");
        userRespository.save(user);
        return "ok";
    }

    /*列表*/
    @ApiOperation(value = "获取用户列表",notes = "获取用户列表，用户列表的信息主要包含。。。。",
            response=MyPageImpl.class,httpMethod="GET")
    @RequestMapping("list")
    @ApiResponse(code = 200,message="返回的MyPageImpl对象",response=MyPageImpl.class)
    public MyPageImpl<User> list(@ApiParam(name="userVo",value = "UserVo的对象",defaultValue = "")
            @RequestBody UserVo userVo){
        System.out.print(" 服务提供者  参数是  " + userVo);
        MyPageImpl<User> userPage = userService.list(userVo);
        log.info(" 获取数据了。。。。。。。。。。。。");
        userPage.getContent().iterator().forEachRemaining(x->{System.out.println("x is " + x);});
        log.info("page.class is " + userPage.getClass());
        return userPage;
    }

    /*删除*/
    @RequestMapping("del")
    public boolean del(@RequestParam("id") int id){
        return userService.del(id);
    }

    /*添加*/
    @RequestMapping("add")
    public boolean add(@RequestBody User user){
        return userService.add(user);
    }
    @RequestMapping("departs")
    public List<Depart> getDeparts(){
        return userService.listdeparts();
    }

    @RequestMapping("favourites")
    public List<Favourite> getfavourites(){
        return userService.listFavourites();
    }

}

