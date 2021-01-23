package com.layui.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 35293
 * @date 2021/1/5 16:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataVO<T> {
    private Integer code;
    private String msg;
    private Long count;
    private List<T> data;
}
