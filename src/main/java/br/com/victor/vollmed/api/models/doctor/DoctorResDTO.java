package br.com.victor.vollmed.api.models.doctor;

import br.com.victor.vollmed.api.models.address.Address;
import br.com.victor.vollmed.api.models.address.AddressResDTO;
import br.com.victor.vollmed.api.utils.Constants;

import java.util.UUID;

public record DoctorResDTO(
    UUID id,
    String name,
    String email,
    String phone,
    String crm,
    Constants.Speciality speciality,
    AddressResDTO address) {
  public DoctorResDTO(Doctor doctor) {
    this(
        doctor.getId(),
        doctor.getName(),
        doctor.getEmail(),
        doctor.getPhone(),
        doctor.getCrm(),
        doctor.getSpeciality(),
        getAddressRes(doctor.getAddress()));
  }

  private static AddressResDTO getAddressRes(Address address) {
    return new AddressResDTO(address);
  }
}
