package com.example.server.service;


import com.example.server.model.User;
import com.example.server.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepo userRepo;


    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Users addUser(Users users){
        return userRepo.save(users);
    }

    public List<Users> findAllUsers(){
        return userRepo.findAll();
    }

    public Users updateUser(Users users){
        return userRepo.save(users);
    }

    public void deleteUser(Long id){
        userRepo.deleteById(id);
    }


}
