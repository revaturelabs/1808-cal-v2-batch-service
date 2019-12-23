package com.revaturelabs.caliber.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.revaturelabs.caliber.batch"})
@EnableFeignClients
public class BatchServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(BatchServiceApplication.class, args);
  }

}
