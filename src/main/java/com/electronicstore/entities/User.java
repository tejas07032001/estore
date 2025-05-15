package com.electronicstore.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;

    @Column(name = "User_name")
    private String name;

    @Column(name = "user_email",unique = true)
    private String email;

    @Column(name = "User_Password",length = 10)
    private String password;

    private String gender;

    @Column(length = 500)
    private String about;

    @Column(name = "user_image_name")
    private String imageName;


}
