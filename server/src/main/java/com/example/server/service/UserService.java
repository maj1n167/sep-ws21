package com.example.server.service;
import com.example.server.model.User;
import com.example.server.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    private final UserRepo userRepo;

        @Autowired
        public UserService(UserRepo userRepo) {
            this.userRepo = userRepo;
        }

        public User addUser(User users){
            return userRepo.save(users);
        }

        public List<User> findAllUsers(){
            return userRepo.findAll();
        }

        public User updateUser(User users){
            return userRepo.save(users);
        }

        public void deleteUser(int id){
            userRepo.deleteById(id);
        }



    }
