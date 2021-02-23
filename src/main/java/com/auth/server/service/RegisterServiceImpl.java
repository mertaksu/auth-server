package com.auth.server.service;

import com.auth.server.entity.User;
import com.auth.server.exception.ConfirmedPasswordNotEqualsException;
import com.auth.server.exception.UserAlreadyExistException;
import com.auth.server.pojo.RegisterRequest;
import com.auth.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RegisterServiceImpl implements RegisterService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserRepository userRepository;

    @Override
    public Boolean register(RegisterRequest registerRequest) throws ConfirmedPasswordNotEqualsException, UserAlreadyExistException {
        try {
            User foundUser = userRepository.findByUserName(registerRequest.getUsername());
            if (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword()))
                throw new ConfirmedPasswordNotEqualsException("Confirmed password not equals");
            else if(foundUser!=null) {
                throw new UserAlreadyExistException("Username already exist.");
            } else {
                User user = new User();
                user.setUserName(registerRequest.getUsername());
                user.setUserPass(bCryptPasswordEncoder.encode(registerRequest.getPassword()));
                user.setUserEmail(registerRequest.getEmail());
                user.setUserGsm(registerRequest.getPhone());
                userRepository.save(user);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
