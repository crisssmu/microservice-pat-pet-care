package com.microservice;

public class ProviderMapper {
    public ProviderDTO toDTO(Providers entity) {
        return new ProviderDTO(entity.getName(), entity.getEmail(), entity.getDocument(), entity.getPhoneNumber(),
                entity.getGender(), entity.getRole());
    }

    public Providers toEntity(ProviderDTO dto) {
        Providers provider = new Providers();
        provider.setName(dto.getName());
        provider.setEmail(dto.getEmail());
        provider.setDocument(dto.getDocument());
        provider.setPhoneNumber(dto.getPhoneNumber());
        provider.setGender(dto.getGender());
        provider.setRole(dto.getRole());
        return provider;
    }

}
