package com.hescha.mobile_services.repository;

import com.hescha.mobile_services.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {
    List<Provider> findByNameLike(String name);
}