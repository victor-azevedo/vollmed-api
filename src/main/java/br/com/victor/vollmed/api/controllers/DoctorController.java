package br.com.victor.vollmed.api.controllers;

import br.com.victor.vollmed.api.dto.DoctorPostDTO;
import br.com.victor.vollmed.api.dto.DoctorUpdateDTO;
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
import java.util.UUID;

@RestController
@Validated
@RequestMapping("/doctors")
public class DoctorController {
  @Autowired private DoctorRepository doctorRepository;

  @PostMapping
  @Transactional
  public ResponseEntity<HttpStatus> register(@Valid @RequestBody DoctorPostDTO data) {
    doctorRepository.save(new DoctorModel(data));
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping
  public ResponseEntity<List<DoctorModel>> getAllDoctors() {
    List<DoctorModel> doctorList = doctorRepository.findByIsActive(true);
    return ResponseEntity.status(HttpStatus.OK).body(doctorList);
  }

  @PutMapping("/{id}")
  @Transactional
  public void update(
      @PathVariable(value = "id") UUID id, @Valid @RequestBody DoctorUpdateDTO data) {
    var doctor = doctorRepository.getReferenceById(id);
    doctor.updateInfo(data);
  }
}
