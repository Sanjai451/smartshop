package com.smartshop.SmartShop.service;
import com.smartshop.SmartShop.model.Role;
import com.smartshop.SmartShop.repository.UsersRepository;
import com.smartshop.SmartShop.dto.UserRegisterDTO;
import com.smartshop.SmartShop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UsersRepository userRepo;

    public void registerUser(UserRegisterDTO dto) {

        // 🔒 Business validation
        if (userRepo.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword()); // hash later
        user.setRole(Role.CUSTOMER);

        userRepo.save(user);
    }
}
