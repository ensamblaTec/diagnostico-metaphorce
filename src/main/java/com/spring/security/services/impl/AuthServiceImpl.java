package com.spring.security.services.impl;

import com.nimbusds.jose.JOSEException;
import com.spring.security.persistence.entities.UserEntity;
import com.spring.security.persistence.repositories.UserRepository;
import com.spring.security.services.IAuthService;
import com.spring.security.services.IJWTUtilityService;
import com.spring.security.services.models.dtos.LoginDTO;
import com.spring.security.services.models.dtos.ResponseDTO;
import com.spring.security.services.models.validations.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class AuthServiceImpl implements IAuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IJWTUtilityService jwtUtilityService;

    @Autowired
    private UserValidation userValidation;

    @Override
    public HashMap<String, String> login(LoginDTO login) {
        try {
            HashMap<String, String> jwt = new HashMap<>();
            Optional<UserEntity> user = userRepository.findByEmail(login.getEmail());

            if (user.isEmpty()) {
                jwt.put("error", "user not registered");
                return jwt;
            }

            // verify password
            if (verifyPassword(login.getPassword(), user.get().getPassword())) {
                jwt.put("token", jwtUtilityService.generateJWT(user.get().getId()));
            } else {
                jwt.put("error", "authentication has failed");
            }
            return jwt;
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException | JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseDTO register(UserEntity user) throws Exception {
        try {
            ResponseDTO response = userValidation.validate(user);

            if (response.getNumOfErrors()  > 0) {
                return response;
            }

            List<UserEntity> getAllUsers = userRepository.findAll();

            for (UserEntity repeatFields : getAllUsers) {
                if (repeatFields != null) {
                    response.setNumOfErrors(1);
                    response.setMessage("user already exists");
                    return response;
                }
            }

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
            user.setPassword(encoder.encode(user.getPassword())); // encrypt password
            userRepository.save(user); // insert on database
            response.setMessage("user created successfully");

            return response;
        } catch (Exception e) {
            throw new Exception(e.toString());
        }
    }

    private boolean verifyPassword(String enteredPassword, String storedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(enteredPassword, storedPassword);
    }
}
