package com.jiadonghua.jpa.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import java.io.Serializable;
import java.lang.annotation.Target;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "hg_user")
@Data
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;
    private String username;
    private String password;
    private String name;
    private String email;
    private String telephone;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private int sex;
    private int state;
    private String code;

    /*一对一*/
    //针对的类型,级联关系,user和drivercar是完整的一对一关系(如果你删除user这个drivetcard也要删除),立即加载:例如去完user数据立即能取出drivercarde关联的数据
    @OneToOne(targetEntity = DriverCard.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    //referencedColumnName:被依赖的那张表的主键的id,name:表示关联的id在这张表该起个什么名字,insertable:插入user表时,子表能够添加,
    @JoinColumn(name = "card_id",referencedColumnName = "id",insertable = true,updatable = false,nullable = false)
    private DriverCard driverCard;

    /*多对一*/
    @ManyToOne(targetEntity=Depart.class,cascade = CascadeType.DETACH,fetch = FetchType.EAGER)
    @JoinColumn(name="depart_id",referencedColumnName = "id",insertable = true,updatable = true,nullable = true,
            foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    @NotFound(action = NotFoundAction.IGNORE)
    private Depart depart;

    /*一对多*/
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",referencedColumnName = "uid",insertable = true,updatable = true)
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Room> roomList;

    /*多对多*/
    @ManyToMany(cascade = CascadeType.DETACH,fetch = FetchType.EAGER)
    @JoinTable(name="hg_user_favourite",
            // 本表与中间表的关系 one to many
            joinColumns={@JoinColumn(name = "user_id",referencedColumnName = "uid",foreignKey=@ForeignKey(value = ConstraintMode.NO_CONSTRAINT))},
            // 描述的中间表与目标表的对应关系  many to one
            inverseJoinColumns = {@JoinColumn(name = "favourite_id",referencedColumnName = "id",foreignKey=@ForeignKey(value = ConstraintMode.NO_CONSTRAINT))},
            //唯一约束
            uniqueConstraints={@UniqueConstraint(name="unit",columnNames={"user_id","favourite_id"})}
    )
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Favourite> favouriteList;

}

