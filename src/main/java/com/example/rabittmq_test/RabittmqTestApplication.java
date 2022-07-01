package com.example.rabittmq_test;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RabittmqTestApplication implements CommandLineRunner {

  @Autowired
  private RabbitTemplate rabbitTemplate;

  public static void main(String[] args) {
    SpringApplication.run(RabittmqTestApplication.class, args);
  }

  @Bean
  public Queue myQueue() {
    return new Queue("myQueue", false);
  }

  @RabbitListener(queues = "myQueue")
  public void listen(String in) {
    System.out.println("Message read from myQueue : " + in);
  }

  @Override
  public void run(String... args) throws Exception {
    rabbitTemplate.convertAndSend("myQueue", "Hello, world!");
  }
}
