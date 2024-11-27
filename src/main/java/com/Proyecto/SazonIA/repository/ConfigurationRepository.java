package com.Proyecto.SazonIA.repository;

import com.Proyecto.SazonIA.model.ConfigurationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfigurationRepository extends JpaRepository<ConfigurationModel, Integer> {
    Optional<ConfigurationModel> findByUserProfile_UserId(Integer userId);
} 