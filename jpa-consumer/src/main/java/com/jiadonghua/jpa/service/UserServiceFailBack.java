package com.jiadonghua.jpa.service;

import com.jiadonghua.jpa.entity.*;

import java.util.ArrayList;
import java.util.List;

public class UserServiceFailBack implements  UserService {

    @Override
    public MyPageImpl<User> list(UserVo userVo) {
        System.out.println(" 对不起，熔断了。。。。");
        return  null;
    }

    @Override
    public boolean del(int id) {
        return false;
    }

    @Override
    public boolean add(User user) {
        return false;
    }

    @Override
    public List<Depart> listdeparts() {
        return new ArrayList<>();
    }

    @Override
    public List<Favourite> listFavourites() {
        return new ArrayList<>();
    }

}

