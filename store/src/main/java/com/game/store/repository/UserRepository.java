package com.game.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.game.store.user.Users;

public interface UserRepository extends JpaRepository<Users, String>  {
	UserDetails findByLogin(String login);
}
