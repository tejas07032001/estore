package com.electronicstore.dtos;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

//To sending data into object (Json) we use these class instede of String

public class ApiResponseMessage {

    private String message;
    private Boolean success;
    private HttpStatus status;

}
