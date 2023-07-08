package com.pharmacyPortal.ServicesImpl;

import com.pharmacyPortal.Exception.UserNotFoundException;
import com.pharmacyPortal.entity.User;
import com.pharmacyPortal.repository.UserRepository;
import com.pharmacyPortal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {



    @Autowired
    private UserRepository userRepository;
    @Override
    public User addUser(User user) {

        return this.userRepository.save(user);
    }

    @Override
    public List<User> getAllCustomer() {
        return this.userRepository.findAllByCustomer();
    }

    @Override
    public User updateUser(User user, Integer id) {
        User prevUser = this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with Id " + id));
        prevUser.setName(user.getName());
        prevUser.setPassword(user.getPassword());
        prevUser.setEmail(user.getEmail());
        return this.userRepository.save(prevUser);
    }

    @Override
    public List<User> getAllDistributor() {
        return this.userRepository.findAllByDistributor();
    }

    @Override
    public void deleteUserById(Integer id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with Id " + id));
        this.userRepository.delete(user);
    }

    @Override
    public User getUserById(Integer id) {
        return this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with Id " + id));
    }

    @Override
    public User findUserByEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found with this email " + email));
    }
}
