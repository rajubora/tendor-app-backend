package com.tender.service.impl;

import com.tender.Repository.UserRepository;
import com.tender.constants.TenderConstants;
import com.tender.entity.User;
import com.tender.model.LoginRequest;
import com.tender.model.LoginResponse;
import com.tender.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service


public class UserServiceImpl implements UserService{



        @Autowired
        UserRepository userRepository;
        @Autowired
        PasswordEncoder encoder;

@Override
        public User addUser(User user) {
            user.setUserId(UUID.randomUUID().toString());
            user.setCurrentStatus(true);
            user.setCreatedAt(LocalDateTime.now());

            String planepassword = user.getPassword();
            String encryptedpassword=encoder.encode(planepassword);
            user.setPassword(encryptedpassword);
            userRepository.save(user);

            return user;
            }
@Override
            public LoginResponse login(LoginRequest request){
    LoginResponse res=new LoginResponse();
    // Verify if User Exist Or not

    Optional<User> useroptional=userRepository.findByEmailOrMobile(request.getUsername(),request.getUsername() );
if (useroptional.isPresent())
{ User user=useroptional.get();
    boolean isPasswordMatches=encoder.matches(user.getPassword(),user.getPassword() );
    if (isPasswordMatches)
    {
        //jwt token issue
        res.setStatus(true);
        res.setMessage("login Successful");
    }
    else {
        res.setStatus(false);
        res.setMessage("invalid Username or Password");
    }

}
else {
 res.setStatus(false);
 res.setMessage(TenderConstants.NO_USER_EXISTS_MSG);

}
    return  res;

}


}



