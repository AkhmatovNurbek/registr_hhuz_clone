package uz.hh.hh_clone_reg.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.hh.hh_clone_reg.dtos.ApiResponse;
import uz.hh.hh_clone_reg.dtos.RegisterDTO;
import uz.hh.hh_clone_reg.service.Authservice;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    @Autowired
    Authservice authservice;

    @PostMapping("/register")
    public HttpEntity<?> registerUser( @Valid @RequestBody RegisterDTO registerDTO) {
        ApiResponse apiResponse = authservice.registerUser(registerDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
