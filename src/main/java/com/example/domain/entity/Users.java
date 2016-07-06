package com.example.domain.entity;

import com.example.db.interfaces.DBEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users implements DBEntity { 

 
 private String name; 
 private int age; 
 
}