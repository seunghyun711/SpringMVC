package com.example.coffee;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class CoffeePostDto {
    @NotBlank(message = "이름은 공백이 아니어야 합니다.")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]+(\\s?[a-zA-Z])*$",
            message = "영문만 가능하고, 영문 워드 사이에 한칸의 공백만 허용합니다.")
    private String engName;

    @NotBlank(message = "이름은 공백이 아니어야 합니다.")
    private String korName;
    private int price;
}
