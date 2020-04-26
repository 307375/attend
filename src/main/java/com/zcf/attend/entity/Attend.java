package com.zcf.attend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attend implements Serializable {

    private Integer id;

    private String name;

    private Integer sign;
}
