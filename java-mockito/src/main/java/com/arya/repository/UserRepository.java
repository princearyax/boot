package com.arya.repository;

public interface UserRepository {
    boolean save(String username);
    String findByUserName(String username);
}
