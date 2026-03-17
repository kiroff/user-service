package org.kiroff.user_service.controllers;

import org.kiroff.user_service.dto.User;
import org.kiroff.user_service.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController
{
    @Autowired
    UsersService usersService;

    @GetMapping("/")
    public List<User> getUsers(@RequestParam("limit") Integer limit, @RequestParam("offset") Integer offset)
    {
        return Collections.emptyList();

    }

    @GetMapping("/{userName}")
    public User getUser(@PathVariable("userName") String userName)
    {

        return usersService.getUserDetails(userName);

    }

    @PostMapping("/{userName}")
    public boolean verifyUserPassword(@PathVariable("userName") String userName,
            @RequestParam("password")                                                                                                                                                                                String password) {

        User user = usersService.getUserDetails(userName, password);

        return user != null;
    }
}
