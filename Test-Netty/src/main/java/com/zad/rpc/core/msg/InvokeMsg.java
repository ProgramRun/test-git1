package com.zad.rpc.core.msg;

import lombok.Data;

import java.io.Serializable;

/**
 * 描述:
 *
 * @author zad
 * @create 2018-09-27 19:00
 */
@Data
public class InvokeMsg implements Serializable {

    private static final long serialVersionUID = 8507448094407097942L;
    private String className;

    private String methodName;

    private Class<?>[] params;

    private Object[] values;

}
