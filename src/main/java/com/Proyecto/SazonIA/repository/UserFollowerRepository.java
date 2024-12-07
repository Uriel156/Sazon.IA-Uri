package com.Proyecto.SazonIA.repository;

import com.Proyecto.SazonIA.model.UserFollowerModel;
import com.Proyecto.SazonIA.model.UserFollowerPK;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserFollowerRepository extends JpaRepository<UserFollowerModel, UserFollowerPK> {
    @Query(value = "SELECT * FROM user_follower uf WHERE uf.follower_id = :userId", nativeQuery = true)
    List<UserFollowerModel> findFollowersByUserId(@Param("userId") Integer userId, Pageable pageable);

    @Query(value = "SELECT * FROM user_follower uf WHERE uf. = :followerId", nativeQuery = true)
    List<UserFollowerModel> findFollowingsByFollowerId(@Param("followerId") Integer followerId, Pageable pageable);
}
