package br.com.victor.vollmed.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record DoctorUpdateDTO(
    String name,
    @Email(message = "The 'email' is required and must be valid.") String email,
    @Pattern(
            regexp = "^\\d{" + DoctorUpdateDTO.PHONE_MIN_LENGTH + ",}",
            message =
                "The 'phone' is required and must have at least "
                    + DoctorUpdateDTO.PHONE_MIN_LENGTH
                    + " digits.")
        String phone,
    @Valid AddressUpdateDTO address) {
  private static final int PHONE_MIN_LENGTH = 10;
}
