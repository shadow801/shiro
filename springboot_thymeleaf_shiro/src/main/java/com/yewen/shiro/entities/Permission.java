package com.yewen.shiro.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author ShadowStart
 * @create 2021-07-20 21:51
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Permission implements Serializable {

    private Integer id;
    private String name;
    private String url;
}
