package com.Proyecto.SazonIA.repository;

import com.Proyecto.SazonIA.model.userProfileModel; // Aseg�rate de que el nombre del modelo est� en may�scula
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userProfileRepository extends JpaRepository<userProfileModel, Integer> {
    // Aqu� puedes agregar m�todos personalizados si es necesario
}

