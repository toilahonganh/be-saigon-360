package com.be_servicie.saigon_travel.be_service.exception;


import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthException extends RuntimeException {

    // Constructor mặc định
    public AuthException() {
        super();
    }

    // Constructor với thông điệp
    public AuthException(String message) {
        super(message);
    }

    // Constructor với thông điệp và nguyên nhân
    public AuthException(String message, Throwable cause) {
        super(message, cause);
    }

    // Phương thức static để tạo exception cho trường hợp email đã tồn tại
    public static AuthException emailExist() {
        return new AuthException("Email đã tồn tại");
    }

    // Các phương thức khác có thể được thêm vào tùy thuộc vào yêu cầu
    public static AuthException invalidCredentials() {
        return new AuthException("Thông tin đăng nhập không hợp lệ.");
    }

    public static AuthException accessDenied() {
        return new AuthException("Bạn không có quyền truy cập.");
    }
}