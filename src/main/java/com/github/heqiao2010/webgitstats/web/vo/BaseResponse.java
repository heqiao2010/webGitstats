package com.github.heqiao2010.webgitstats.web.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BaseResponse {
    private boolean success;
    private String message;
}
