package com.flashsale.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @auther chenjiajun1999
 * @date 6/6 11:07
 */
@Getter
@Setter
public class StockOrder {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private Integer sid;

    private String name;

    private Date createTime;
}
