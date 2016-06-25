package com.example.data;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Table 
public class users { 

 
 private String name; 
 private int age; 
 
 public users(String name, int age) { 
  this.name = name; 
  this.age = age; 
 } 

 public String getName() { 
  return name; 
 } 
 
 public int getAge() { 
  return age; 
 } 
 
 @Override 
 public String toString() { 
  return "Person [name=" + name + ", age=" + age + "]"; 
 } 
 
}