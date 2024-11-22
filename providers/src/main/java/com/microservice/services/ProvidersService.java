package com.microservice.services;

import java.util.List;

import com.microservice.ProviderDTO;
import com.microservice.ProviderMapper;
import com.microservice.Providers;
import com.microservice.repository.ProviderRepository;



public class ProvidersService implements InProviderService {

    ProviderRepository providerRepository = new ProviderRepository();
    @Override
    public void registerProvider(ProviderDTO providerDTO) {
        if(providerDTO != null){
            ProviderMapper providerMapper = new ProviderMapper();
            Providers provider = providerMapper.toEntity(providerDTO);
            providerRepository.registerProvider(provider);
        }
    }

    @Override
    public List<Providers> getAllProviders() {
        return providerRepository.getProviders();
    }

    @Override
    public long getProviderByDocument(long document) {
        return providerRepository.getProviderById(document);
    }

    @Override
    public void updateProvider(Providers provider, long id) {
        providerRepository.updateProvider(provider, id);
    }

    @Override
    public void deleteProvider(long id) {
        providerRepository.deleteProvider(id);
    }

    @Override
    public void topUpMoney(long id, float money) {
        Providers provider = new Providers();
        provider.getBalance();
        float balance = provider.getBalance() + money;
        providerRepository.TopUpMoney(id, balance);
    }

}
