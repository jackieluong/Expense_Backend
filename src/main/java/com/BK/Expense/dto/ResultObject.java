package com.BK.Expense.dto;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public class ResultObject {
    public boolean isSuccess;
    public String message;
    public HttpStatus httpStatus;
    public Object data;

    public ResultObject(boolean isSuccess, String message, HttpStatus httpStatus, Object data) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.httpStatus = httpStatus;
        this.data = data;
    }

    public static ResultObject success(Object data, String message) {
        return new ResultObject(true, message, HttpStatus.OK, data);
    }

    public static ResultObject error(String message, HttpStatus status) {
        return new ResultObject(false, message, status, null);
    }
}
