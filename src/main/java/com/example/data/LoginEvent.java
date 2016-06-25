package com.example.data;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import lombok.Data;

@Data
@Table(value = "login_event")
public class LoginEvent {

  @PrimaryKey
  private LoginEventKey pk;

  @Column(value = "event_code")
  private int eventCode;

  @Column(value = "ip_address")
  private String ipAddress;

}