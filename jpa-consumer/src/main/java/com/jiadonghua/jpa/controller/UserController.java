package com.jiadonghua.jpa.controller;

import com.jiadonghua.jpa.entity.*;
import com.jiadonghua.jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("list")
    public MyPageImpl<User> list(UserVo userVo){
        System.out.print(" 消费者参数是  " + userVo);
        MyPageImpl<User> page = userService.list(userVo);
        System.out.println ("  消费者 ========== 已经获取数据了。。。。。。。。。。。。");
        page.getContent().iterator().forEachRemaining(x->{System.out.println("消费者  x is " + x);});
        return page;
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

    /*所有部门*/
    @RequestMapping("departs")
    public List<Depart> getDeparts(){
        return userService.listdeparts();
    }

    /*所有爱好*/
    @RequestMapping("favourites")
    public List<Favourite> getfavourites(){
        return userService.listFavourites();
    }

}
