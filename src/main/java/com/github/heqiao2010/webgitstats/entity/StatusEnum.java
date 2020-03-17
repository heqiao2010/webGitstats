package com.github.heqiao2010.webgitstats.entity;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum StatusEnum {
    INIT(0, "初始状态"),
    CLONING(1, "克隆中"),
    ERROR(-1, "出错"),
    ;

    private int status;
    private String desc;

    StatusEnum(int status, String desc){
        this.status = status;
        this.desc = desc;
    }

    public static StatusEnum fromStatus(int status){
        return Arrays.stream(values()).filter(s -> s.status == status).findFirst().orElse(ERROR);
    }
}
