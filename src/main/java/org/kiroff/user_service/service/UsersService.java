package org.kiroff.user_service.service;

import org.kiroff.user_service.dto.User;
import org.kiroff.user_service.dao.UserEntity;
import org.kiroff.user_service.dao.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private static final Logger LOG = LoggerFactory.getLogger(UsersService.class);

    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UsersService(UsersRepository usersRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usersRepository = usersRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public User getUserDetails(String userName) {
        User returnValue = new User(null, null, null, null, null);

        UserEntity userEntity = usersRepository.findByEmail(userName);
        if (userEntity != null) {
            returnValue = new User(userEntity.getFirstName(), userEntity.getLastName(), userEntity.getEmail(), userName, userEntity.getUserId());
        }

        return returnValue;
    }

    public User getUserDetails(String userName, String password) {
        User returnValue = null;

        UserEntity userEntity = usersRepository.findByEmail(userName);

        if (userEntity != null)
        {

            if (bCryptPasswordEncoder.matches(password, userEntity.getEncryptedPassword()))
            {
                LOG.info("password matches!!!");

                returnValue = new User(userEntity.getFirstName(), userEntity.getLastName(), userEntity.getEmail(),
                        userName, userEntity.getUserId());

            }
            else
            {
                LOG.warn("Incorrect password {}", password);
            }
        }
        return returnValue;
    }
}
