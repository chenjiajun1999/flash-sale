package com.flashsale.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Getter;
import lombok.Setter;

/**
 * @auther chenjiajun1999
 * @date 6/6 11:07
 */
@Getter
@Setter
public class Stock {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer count;

    private Integer sale;

    @Version
    private Integer version;
}
