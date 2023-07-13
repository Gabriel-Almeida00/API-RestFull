package com.gabriel.api.controller;

import com.gabriel.api.data.vo.v1.security.AccountCredentialsVO;
import com.gabriel.api.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authenticaton endpoint")
@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @SuppressWarnings("rawtypes")
    @Operation(summary = "Authenticates a user and return a token")
    @PostMapping(value = "/signin")
    public ResponseEntity signin(@RequestBody AccountCredentialsVO data){
        if(checkIfParamsIsNotNull(data))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Client Request");
        var token = authService.signin(data);
        if(token == null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Client Request");
        return token;
    }

    private boolean checkIfParamsIsNotNull(AccountCredentialsVO data){
        return data == null || data.getUsername() == null || data.getUsername().isBlank()
                || data.getPasswod() == null || data.getPasswod().isEmpty();
    }
}
