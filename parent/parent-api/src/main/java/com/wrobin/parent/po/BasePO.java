package com.wrobin.parent.po;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * created by robin.wu on 2018/5/9
 **/
@Data
public class BasePO implements Serializable{
    @Id
    private Long id;
}
