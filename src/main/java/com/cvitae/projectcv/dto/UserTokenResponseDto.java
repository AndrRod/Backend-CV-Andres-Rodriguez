package com.cvitae.projectcv.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserTokenResponseDto {
    private String email;
    private String access_Token;
    private String refresh_Token;
}
