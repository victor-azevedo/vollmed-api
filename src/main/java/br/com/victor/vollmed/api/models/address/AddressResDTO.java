package br.com.victor.vollmed.api.models.address;

public record AddressResDTO(
    String street,
    String neighborhood,
    String postalCode,
    String city,
    String uf,
    String complement,
    String number) {
  public AddressResDTO(Address address) {
    this(
        address.getStreet(),
        address.getNeighborhood(),
        address.getPostalCode(),
        address.getCity(),
        address.getUf(),
        address.getComplement(),
        address.getNumber());
  }
}
