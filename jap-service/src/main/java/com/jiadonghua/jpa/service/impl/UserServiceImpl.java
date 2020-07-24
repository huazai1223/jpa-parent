package com.jiadonghua.jpa.service.impl;

import com.jiadonghua.jpa.entity.*;

import com.jiadonghua.jpa.repository.DepartsRespostiory;
import com.jiadonghua.jpa.repository.FavouriteRepository;
import com.jiadonghua.jpa.repository.UserRepository;
import com.jiadonghua.jpa.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    DepartsRespostiory departsRespostiory;

    @Autowired
    FavouriteRepository favouriteRepository;

    @Override
    public MyPageImpl<User> list(UserVo userVo) {

        Specification specification  = new Specification<User>() {
            //动态sql查询条件
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                /*
                * Predicate
                * 1 . 代表着一个**“断定式子”*
                2 . 这是一个实用的接口—>其中的实用方法指的是test方法
                * */
                //断定式子
                ArrayList<Predicate> list = new ArrayList<>();//保存查询条件

                //根据username 生成查询条件
                if(!StringUtils.isEmpty(userVo.getUsername())){ //如果登录名不为空
                    Predicate predicate = criteriaBuilder.like(root.get("username"), "%" + userVo.getUsername() + "%");
                    //查询条件保存到集合当中
                    list.add(predicate);
                }
                if(!StringUtils.isEmpty(userVo.getName())){
                    Predicate predicate = criteriaBuilder.like(root.get("name"), "%" + userVo.getName() + "%");
                    //查询条件保存到集合当中
                    list.add(predicate);
                }
                if(userVo.getSex()!=0){
                    Predicate predicate =criteriaBuilder.equal(root.get("sex"),userVo.getSex());
                    list.add(predicate);
                }

                Predicate[] predicates = list.toArray(new Predicate[list.size()]);  //专换为数组  获取条件的数组
                //组合所有查询条件
                Predicate predicateAll = criteriaBuilder.and(predicates);
                return predicateAll;
            }
        };
        // 生成分页的对象
        Pageable pageable = PageRequest.of(userVo.getPage()-1,userVo.getPageSize(), Sort.Direction.DESC,"uid");
        //Page<User> page = userRepository.findAll(pageable);
        //使用动态sql 查询的结果
        Page page = userRepository.findAll(specification,pageable);
        return new MyPageImpl(page);

    }

    @Override
    public boolean del(int id) {
        try {
            userRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean add(User user) {
        User save = userRepository.saveAndFlush(user);
        try {
            if (user.getUid()>0){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public List<Depart> listdeparts() {
        return departsRespostiory.findAll();
    }

    @Override
    public List<Favourite> listFavourites() {
        return favouriteRepository.findAll();
    }
}
