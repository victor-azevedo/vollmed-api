package br.com.victor.vollmed.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class ApiHeathController {
  @GetMapping
  public ResponseEntity<String> health() {
    return ResponseEntity.ok("VollMed API healthy");
  }
}
