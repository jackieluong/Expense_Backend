package com.BK.Expense.dto;

import com.BK.Expense.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    private long id;

    private String email;

    private String name;

    private RoleEnum role;

}
