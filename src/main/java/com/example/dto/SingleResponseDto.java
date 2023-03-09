package com.example.dto;

import lombok.Getter;

@Getter
public class SingleResponseDto<T> {
    private T data;
}
