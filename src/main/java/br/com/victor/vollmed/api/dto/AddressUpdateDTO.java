package br.com.victor.vollmed.api.dto;

import br.com.victor.vollmed.api.utils.Constants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressUpdateDTO(
    String street,
    String neighborhood,
    @Pattern(
            regexp = "^\\d{" + Constants.POSTAL_CODE_LENGTH + "}",
            message =
                "The 'postalCode' is required and must be "
                    + Constants.POSTAL_CODE_LENGTH
                    + " digits.")
        String postalCode,
    String city,
    String uf,
    String complement,
    String number) {}
