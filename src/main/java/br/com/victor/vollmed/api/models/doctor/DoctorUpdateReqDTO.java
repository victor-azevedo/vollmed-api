package br.com.victor.vollmed.api.models.doctor;

import br.com.victor.vollmed.api.models.address.AddressUpdateReqDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record DoctorUpdateReqDTO(
    String name,
    @Email(message = "The 'email' is required and must be valid.") String email,
    @Pattern(
            regexp = "^\\d{" + DoctorUpdateReqDTO.PHONE_MIN_LENGTH + ",}",
            message =
                "The 'phone' is required and must have at least "
                    + DoctorUpdateReqDTO.PHONE_MIN_LENGTH
                    + " digits.")
        String phone,
    @Valid AddressUpdateReqDTO address) {
  private static final int PHONE_MIN_LENGTH = 10;
}
