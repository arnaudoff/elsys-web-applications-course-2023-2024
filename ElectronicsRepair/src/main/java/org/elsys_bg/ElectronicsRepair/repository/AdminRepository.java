package org.elsys_bg.ElectronicsRepair.repository;

import jakarta.transaction.Transactional;
import org.elsys_bg.ElectronicsRepair.entity.Admin;
import org.elsys_bg.ElectronicsRepair.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Admin u WHERE u.name = :name")
    void deleteByUsername(@Param("name") String name);

    @Query("select c from Admin c where c.name = :username")
    Admin findByUsername(String username);

    @Query("select c from Admin c where (c.name = :username and c.password = :password)")
    Admin checkPassword(String username, String password);

    default boolean adminExists(String username){
        Admin admin = findByUsername(username);
        return admin != null;
    }

    default boolean checkAdminPassword(String username, String password){
        Admin admin = checkPassword(username, password);
        return admin != null;
    }
}