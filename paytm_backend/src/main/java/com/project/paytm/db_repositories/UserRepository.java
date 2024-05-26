package com.project.paytm.db_repositories;

import com.project.paytm.db_entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {

    Optional<UserEntity> findByEmail(String username);


    @Query(nativeQuery = true,value = "Select * from user_info where first_name like %:username% or last_name like %:username%")
    Optional<List<UserEntity>> findByUsername(@Param("username") String username);

}
