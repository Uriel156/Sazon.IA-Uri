package com.Proyecto.SazonIA.repository;

import com.Proyecto.SazonIA.model.UserProfileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface userProfileRepository extends JpaRepository<UserProfileModel, Integer> {

    //Search a userProfile by id
    @Query(value = "SELECT * FROM userSazon u WHERE u.user_id = :userId", nativeQuery = true)
    UserProfileModel findUserProfileByUserId(Integer userId);
}
