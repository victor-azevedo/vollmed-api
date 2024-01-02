package br.com.victor.vollmed.api.models.address;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false)
  private String street;

  @Column(nullable = false)
  private String neighborhood;

  @Column(name = "postal_code", nullable = false)
  private String postalCode;

  @Column() private String number;

  @Column() private String complement;

  @Column(nullable = false)
  private String city;

  @Column(nullable = false)
  private String uf;

  public Address(AddressPostReqDTO addressDTO) {
    this.street = addressDTO.street();
    this.neighborhood = addressDTO.neighborhood();
    this.postalCode = addressDTO.postalCode();
    this.number = addressDTO.number();
    this.complement = addressDTO.complement();
    this.city = addressDTO.city();
    this.uf = addressDTO.uf();
  }

  public void updateInfo(AddressUpdateReqDTO address) {
    if (address.street() != null) {
      this.street = address.street();
    }
    if (address.neighborhood() != null) {
      this.neighborhood = address.neighborhood();
    }
    if (address.postalCode() != null) {
      this.postalCode = address.postalCode();
    }
    if (address.uf() != null) {
      this.uf = address.uf();
    }
    if (address.city() != null) {
      this.city = address.city();
    }
    if (address.number() != null) {
      this.number = address.number();
    }
    if (address.complement() != null) {
      this.complement = address.complement();
    }
  }
}
