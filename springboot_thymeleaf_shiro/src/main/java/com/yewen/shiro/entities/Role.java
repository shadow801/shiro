package com.yewen.shiro.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author ShadowStart
 * @create 2021-07-20 21:51
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {

    private Integer id;
    private String name;

    // 定义权限集合
    private List<Permission> permissions;

}
