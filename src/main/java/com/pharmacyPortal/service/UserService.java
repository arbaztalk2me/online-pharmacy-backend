package com.pharmacyPortal.service;

import com.pharmacyPortal.entity.User;

import java.util.List;

public interface UserService {
    User addUser(User user);
    List<User> getAllCustomer();
    User updateUser(User user,Integer id);
    List<User> getAllDistributor();
    void deleteUserById(Integer id);
    User getUserById(Integer id);
    User findUserByEmail(String email);


}
