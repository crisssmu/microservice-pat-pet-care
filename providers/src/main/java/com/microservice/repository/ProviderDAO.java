package com.microservice.repository;

import java.util.List;

import com.microservice.Providers;

public interface ProviderDAO {
    void registerProvider(Providers provider);
    List<Providers> getProviders();
    long getProviderById(long document);
    void updateProvider(Providers provider, long id);
    void deleteProvider(long id);
    void TopUpMoney(long id, float money);
    
}
