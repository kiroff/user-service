package org.kiroff.user_service;

import org.kiroff.user_service.dao.UserEntity;
import org.kiroff.user_service.dao.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class InitialSetup
{
    private static final Logger LOGGER = LoggerFactory.getLogger(InitialSetup.class);
    final BCryptPasswordEncoder bCryptPasswordEncoder;

    final UsersRepository usersRepository;

    public InitialSetup(BCryptPasswordEncoder bCryptPasswordEncoder, UsersRepository usersRepository)
    {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.usersRepository = usersRepository;
    }

    @EventListener
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent event)
    {

        UserEntity user = new UserEntity(
                null,
                "qswe3mg84mfjtu",
                "Бай",
                "Хуй",
                "test2@test.com",
                bCryptPasswordEncoder.encode("sergey"),
                "",
                false);

        usersRepository.save(user);
        LOGGER.info("User {}/{} has been created", user.getEmail(), user.getEncryptedPassword());
    }
}
