package br.com.victor.vollmed.api.models;

import br.com.victor.vollmed.api.dto.DoctorUpdateDTO;
import br.com.victor.vollmed.api.utils.Constants;
import br.com.victor.vollmed.api.dto.DoctorPostDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorModel {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(unique = true, nullable = false)
  private String name;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(unique = true, nullable = false)
  private String phone;

  @Column(unique = true, nullable = false)
  private String crm;

  @Enumerated(EnumType.STRING)
  @Column(unique = true, nullable = false)
  private Constants.Speciality speciality;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "address_id", referencedColumnName = "id")
  private AddressModel address;

  public DoctorModel(DoctorPostDTO doctorDTO) {
    this.name = doctorDTO.name();
    this.email = doctorDTO.email();
    this.phone = doctorDTO.phone();
    this.crm = doctorDTO.crm();
    this.speciality = doctorDTO.speciality();
    this.address = new AddressModel(doctorDTO.address());
  }

  public void updateInfo(DoctorUpdateDTO data) {
    if (data.name() != null) {
      this.name = data.name();
    }
    if (data.email() != null) {
      this.email = data.email();
    }
    if (data.phone() != null) {
      this.phone = data.phone();
    }
    if (data.address() != null) {
      this.address.updateInfo(data.address());
    }
  }
}
