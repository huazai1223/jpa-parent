package com.jiadonghua.jpa.service;

import com.jiadonghua.jpa.entity.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "jpa-service")
public interface UserService {

    @RequestMapping("/user/list")
    MyPageImpl<User> list(@RequestBody UserVo userVo);

    @RequestMapping("/user/del")
    boolean del(@RequestParam("id") int id);

    @RequestMapping("/user/add")
    boolean add(@RequestBody User user);

    @RequestMapping("/user/departs")
    List<Depart> listdeparts();

    @RequestMapping("/user/favourites")
    List<Favourite> listFavourites();
}
