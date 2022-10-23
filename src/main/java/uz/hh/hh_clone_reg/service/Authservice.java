package uz.hh.hh_clone_reg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.hh.hh_clone_reg.domain.AuthUser;
import uz.hh.hh_clone_reg.domain.AuthUserRole;
import uz.hh.hh_clone_reg.dtos.ApiResponse;
import uz.hh.hh_clone_reg.dtos.RegisterDTO;
import uz.hh.hh_clone_reg.repository.UserRepository;

import java.util.Random;

@Service
public class Authservice implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return null;
    }

    public ApiResponse registerUser(RegisterDTO registerDTO) {
        if (userRepository.existsByEmail(registerDTO.getEmail()))
            return new ApiResponse("This email exists", false);
        AuthUser authUser = new AuthUser(registerDTO.getFullName(),
                registerDTO.getEmail(),
                passwordEncoder.encode(registerDTO.getPassword()),
                AuthUserRole.USER);
        int code = new Random().nextInt(999999);
        System.out.println(code);
        authUser.setEmailCode(String.valueOf(code).substring(0, 4));
        userRepository.save(authUser);
        sendEmail(authUser.getEmail(), authUser.getEmailCode());

        return new ApiResponse("Success", true);
    }

    public boolean sendEmail(String sendingEmail, String emailCode) {
        try {

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("axmatov0113@gmail.com");
            message.setTo("axmatov0713@gmail.com");
            message.setSubject("Head Hunter verification code");
            message.setText(emailCode);
            javaMailSender.send(message);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
