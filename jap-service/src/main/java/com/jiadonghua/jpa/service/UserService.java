package com.jiadonghua.jpa.service;

import com.jiadonghua.jpa.entity.*;

import java.util.List;

public interface UserService {

    MyPageImpl<User> list(UserVo userVo);

    boolean del(int id);

    boolean add(User user);

    List<Depart> listdeparts();

    List<Favourite> listFavourites();
}
