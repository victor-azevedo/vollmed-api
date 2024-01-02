package br.com.victor.vollmed.api.controllers;

import br.com.victor.vollmed.api.models.doctor.DoctorPostReqDTO;
import br.com.victor.vollmed.api.models.doctor.DoctorUpdateReqDTO;
import br.com.victor.vollmed.api.models.doctor.Doctor;
import br.com.victor.vollmed.api.models.doctor.DoctorRepository;
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
  public ResponseEntity<HttpStatus> register(@Valid @RequestBody DoctorPostReqDTO data) {
    doctorRepository.save(new Doctor(data));
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping
  public ResponseEntity<List<Doctor>> getAllDoctors() {
    List<Doctor> doctorList = doctorRepository.findByIsActive(true);
    return ResponseEntity.ok(doctorList);
  }

  @PutMapping("/{id}")
  @Transactional
  public ResponseEntity<Doctor> update(
      @PathVariable(value = "id") UUID id, @Valid @RequestBody DoctorUpdateReqDTO data) {
    var doctor = doctorRepository.getReferenceById(id);
    doctor.updateInfo(data);

    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  @Transactional
  public void delete(@PathVariable(value = "id") UUID id) {
    var doctor = doctorRepository.getReferenceById(id);
    doctor.setActive(false);
  }
}
