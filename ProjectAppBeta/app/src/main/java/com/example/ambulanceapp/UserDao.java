package com.example.ambulanceapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

// UserDao.java
// UserDao.java
@Dao
public interface UserDao {
    @Query("SELECT * FROM users WHERE name = :name AND password = :password")
    Users login(String name, String password);

    @Insert
    void insert(Users user);

    @Query("SELECT * FROM users")
    List<Users> getAllUsers();

}


