package com.smartshop.SmartShop.repository;

import com.smartshop.SmartShop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository  extends JpaRepository<User, Long> {
    public boolean existsByEmail(String email);
}
