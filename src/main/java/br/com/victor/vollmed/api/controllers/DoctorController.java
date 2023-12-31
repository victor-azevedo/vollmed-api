package br.com.victor.vollmed.api.controllers;

import br.com.victor.vollmed.api.dto.DoctorDTO;
import br.com.victor.vollmed.api.models.DoctorModel;
import br.com.victor.vollmed.api.repositories.DoctorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Validated
@RequestMapping("/doctors")
public class DoctorController {
  @Autowired private DoctorRepository doctorRepository;

  @PostMapping
  @Transactional
  public ResponseEntity<HttpStatus> register(@Valid @RequestBody DoctorDTO doctorDTO) {
    doctorRepository.save(new DoctorModel(doctorDTO));
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping
  public ResponseEntity<List<DoctorModel>> getAllDoctors() {
    List<DoctorModel> doctorList = doctorRepository.findAll();
    return ResponseEntity.status(HttpStatus.OK).body(doctorList);
  }
}
