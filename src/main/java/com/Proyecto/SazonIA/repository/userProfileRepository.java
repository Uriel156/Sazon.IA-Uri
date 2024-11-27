package com.Proyecto.SazonIA.repository;

import com.Proyecto.SazonIA.model.userProfileModel; // Asegúrate de que el nombre del modelo esté en mayúscula
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userProfileRepository extends JpaRepository<userProfileModel, Integer> {
    // Aquí puedes agregar métodos personalizados si es necesario
}

