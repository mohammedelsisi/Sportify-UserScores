package com.example.SportifyUserScores.model.dto;

import com.example.SportifyUserScores.model.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String email;
    private String userName;
    private UserRole userRole;
}
