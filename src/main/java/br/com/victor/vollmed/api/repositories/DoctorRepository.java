package br.com.victor.vollmed.api.repositories;

import br.com.victor.vollmed.api.models.DoctorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorModel, UUID> {
  List<DoctorModel> findByIsActive(Boolean status);
}
