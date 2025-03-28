package com.BK.Expense.controller;

import com.BK.Expense.dto.AccountDto;
import com.BK.Expense.dto.LoginDto;
import com.BK.Expense.dto.RegisterDto;
import com.BK.Expense.dto.ResultObject;

import com.BK.Expense.entity.Account;
import com.BK.Expense.security.JwtAuthResponse;
import com.BK.Expense.security.JwtUtil;
import com.BK.Expense.service.IAccountService;
import com.BK.Expense.service.IAuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RequestMapping("/api/v1/auth")
@RestController
public class AuthController {
    private IAuthService authService;
    private IAccountService accountService;
    private JwtUtil jwtUtil;

    public AuthController(IAuthService authService, IAccountService accountService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.accountService = accountService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<ResultObject> login(@Valid @RequestBody LoginDto loginDto) {

//        Account user = userService.getUserByEmail(loginDto.getUsernameOrEmail());
        Account user = accountService.getAccountByEmail(loginDto.getUsernameOrEmail());

        JwtAuthResponse.UserLogin userLogin = new JwtAuthResponse.UserLogin(
                user.getId(), user.getEmail(), user.getName(), user.getRole());

        String accessToken = authService.login(loginDto, user);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(accessToken);
        jwtAuthResponse.setUserLogin(userLogin);

//        String refreshToken = jwtTokenProvider.createRefreshToken(loginDto.getUsernameOrEmail(), jwtAuthResponse);

//        userService.updateUserToken(refreshToken, loginDto.getUsernameOrEmail());

        // ResponseCookie responseCookie = ResponseCookie
        // .from("refreshToken", refreshToken)
        // .httpOnly(true)
        // .path("/")
        // .maxAge(refreshTokenExpiration)
        // .build();
        ResultObject response = ResultObject.builder()
                .isSuccess(true)
                .message("Login successfully")
                .httpStatus(HttpStatus.OK)
                .data(jwtAuthResponse)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PostMapping("/register")
    public ResponseEntity<ResultObject> register(@Valid @RequestBody RegisterDto registerDto) {


            AccountDto userDto = authService.registerUser(registerDto);

            ResultObject response = ResultObject.builder()
                    .isSuccess(true)
                    .message("Register successfully")
                    .httpStatus(HttpStatus.CREATED)
                    .data(userDto)
                    .build();

            return new ResponseEntity<>(response, HttpStatus.CREATED);
//        } catch (Exception e) {
//
//            ResultObject errorResponseDto = ResultObject.builder()
//                    .isSuccess(false)
//                    .message("Register failed")
//                    .httpStatus(HttpStatus.BAD_REQUEST)
//                    .build();
//
//            // Trả về ResponseEntity với lỗi
//            return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
//
//
//        }
    }


}
