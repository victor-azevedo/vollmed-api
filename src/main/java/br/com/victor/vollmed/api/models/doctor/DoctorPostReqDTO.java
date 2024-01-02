package br.com.victor.vollmed.api.models.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import br.com.victor.vollmed.api.models.address.AddressPostReqDTO;
import static br.com.victor.vollmed.api.utils.Constants.PHONE_MIN_LENGTH;
import static br.com.victor.vollmed.api.utils.Constants.Speciality;

public record DoctorPostReqDTO(
    @NotBlank(message = "The 'name' is required.") String name,
    @Email(message = "The 'email' is required and must be valid.") String email,
    @NotBlank
        @Pattern(
            regexp = "^\\d{" + PHONE_MIN_LENGTH + ",}",
            message =
                "The 'phone' is required and must have at least " + PHONE_MIN_LENGTH + " digits.")
        String phone,
    @NotBlank(message = "The 'crm' is required.") String crm,
    @NotNull(message = "The 'speciality' is required.") Speciality speciality,
    @Valid @NotNull AddressPostReqDTO address) {}
