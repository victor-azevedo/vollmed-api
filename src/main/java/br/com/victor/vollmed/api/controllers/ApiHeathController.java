package br.com.victor.vollmed.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class ApiHeathController {
  @GetMapping
  public String health() {
    return "VollMed API healthy";
  }
}
