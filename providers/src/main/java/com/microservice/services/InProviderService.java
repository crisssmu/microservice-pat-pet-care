package com.microservice.services;

import java.util.List;

import com.microservice.ProviderDTO;
import com.microservice.Providers;


public interface InProviderService {
    void registerProvider(ProviderDTO providerDTO);
    List<Providers> getAllProviders();
    long getProviderByDocument(long document);
    void updateProvider(Providers provider, long id);
    void deleteProvider(long id);
    void topUpMoney(long id, float money);
}
