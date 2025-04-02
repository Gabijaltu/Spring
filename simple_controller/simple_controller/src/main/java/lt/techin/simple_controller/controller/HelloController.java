package lt.techin.simple_controller.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

@RequestMapping("/api")
public class HelloController {

  private String hello = "Hello, Spring Web!";

  @GetMapping("/hello")
  public ResponseEntity<String> hello() {
    return ResponseEntity.ok(hello);
  }


}
