package com.electronicstore.dtos;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class UserDto {



    private String userId;

    @Size(min = 3,max = 15,message = "Invalid name")
    private String name;

   @Email(message = "Invalid email")
   // @Pattern(regexp = "^[a-zA-Z0-9. _%+-]+@[a-zA-Z0-9. -]+\\\\\\\\. [a-zA-Z]{2,}$",message = "Invalid email")
    private String email;

    @NotBlank(message = "password is required")
    private String password;

    @Size(min = 4,max = 15,message = "Invalid gender")
    private String gender;

    @NotBlank(message = "Invalid")
    private String about;


    //@patter
    //@Custome validator for creating our own validation using another package , class, interface

    private String imageName;

}
