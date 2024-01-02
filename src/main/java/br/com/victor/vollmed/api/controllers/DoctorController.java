package br.com.victor.vollmed.api.controllers;

import br.com.victor.vollmed.api.models.doctor.*;
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
  public ResponseEntity<DoctorResDTO> register(@Valid @RequestBody DoctorPostReqDTO data) {
    Doctor doctor = doctorRepository.save(new Doctor(data));

    DoctorResDTO doctorRes = new DoctorResDTO(doctor);
    return ResponseEntity.status(HttpStatus.CREATED).body(doctorRes);
  }

  @GetMapping
  public ResponseEntity<List<DoctorResDTO>> getByIsActive(
      @RequestParam(value = "is_active", defaultValue = "true") boolean isActive) {
    List<Doctor> doctorList = doctorRepository.findByIsActive(isActive);

    List<DoctorResDTO> doctorListRes = doctorList.stream().map(DoctorResDTO::new).toList();
    return ResponseEntity.ok(doctorListRes);
  }

  @GetMapping({"/{id}"})
  public ResponseEntity<DoctorResDTO> getById(@PathVariable(value = "id") UUID id) {
    Doctor doctor = doctorRepository.getReferenceById(id);

    DoctorResDTO doctorRes = new DoctorResDTO(doctor);
    return ResponseEntity.ok(doctorRes);
  }

  @PutMapping("/{id}")
  @Transactional
  public ResponseEntity<DoctorResDTO> update(
      @PathVariable(value = "id") UUID id, @Valid @RequestBody DoctorUpdateReqDTO data) {
    Doctor doctor = doctorRepository.getReferenceById(id);
    doctor.updateInfo(data);

    DoctorResDTO doctorRes = new DoctorResDTO(doctor);
    return ResponseEntity.ok(doctorRes);
  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity<HttpStatus> delete(@PathVariable(value = "id") UUID id) {
    Doctor doctor = doctorRepository.getReferenceById(id);
    doctor.setActive(false);

    return ResponseEntity.noContent().build();
  }
}
