package com.tinie.Update.User.requests;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class UpdateUserRequest {
    @ApiModelProperty(required = true, value = "The User's phone number")
    @Min(1)
    private long phonenumber;

    @ApiModelProperty(required = true, value = "Username")
    @NotBlank(message = "Username must not be empty")
    private String username;
}