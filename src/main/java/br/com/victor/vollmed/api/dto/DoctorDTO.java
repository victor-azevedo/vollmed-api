package br.com.victor.vollmed.api.dto;

import br.com.victor.vollmed.api.enums.Speciality;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DoctorDTO(
    @NotBlank(message = "The 'name' is required.") String name,
    @Email(message = "The 'email' is required and must be valid.") String email,
    @NotBlank
        @Pattern(
            regexp = "^\\d{" + DoctorDTO.PHONE_MIN_LENGTH + ",}",
            message =
                "The 'phone' is required and must have at least "
                    + DoctorDTO.PHONE_MIN_LENGTH
                    + " digits.")
        String phone,
    @NotBlank(message = "The 'crm' is required.") String crm,
    @NotNull(message = "The 'speciality' is required.") Speciality speciality) {
  private static final int PHONE_MIN_LENGTH = 10;
}
