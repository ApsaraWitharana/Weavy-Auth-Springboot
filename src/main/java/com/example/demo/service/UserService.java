package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
/**
 * @author : sachini Apsara
 * @date : 2025-04-04
 * @Project: Sentura Technologies Interview Task user auth
 **/

@Service
public interface UserService {
        String createUser(String jsonPayload) throws IOException;
        String listUsers(int take) throws IOException;
        String getUserDetails(String userId) throws IOException;
        String updateUser(String userId, String jsonPayload) throws IOException;
        String deleteUser(String userId) throws IOException;
}
