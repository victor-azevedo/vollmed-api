package br.com.victor.vollmed.api.dto;

import br.com.victor.vollmed.api.utils.Constants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressPostDTO(
    @NotBlank(message = "The 'street' is required.") String street,
    @NotBlank(message = "The 'neighborhood' is required.") String neighborhood,
    @NotBlank
        @Pattern(
            regexp = "^\\d{" + Constants.POSTAL_CODE_LENGTH + "}",
            message =
                "The 'postalCode' is required and must be "
                    + Constants.POSTAL_CODE_LENGTH
                    + " digits.")
        String postalCode,
    @NotBlank(message = "The 'city' is required.") String city,
    @NotBlank(message = "The 'uf' is required.") String uf,
    String complement,
    String number) {}
