package org.elsys_bg.ElectronicsRepair.repository;

import jakarta.transaction.Transactional;
import org.elsys_bg.ElectronicsRepair.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
    @Transactional
    @Modifying
    @Query("DELETE FROM Client u WHERE u.name = :name")
    void deleteByUsername(@Param("name") String name);

    @Query("select c from Client c where c.name = :username")
    Client findByUsername(String username);

    @Query("select c from Client c where (c.name = :username and c.password = :password)")
    Client checkPassword(String username, String password);

    default boolean userExists(String username){
        Client client = findByUsername(username);
        return client != null;
    }

    default boolean checkClientPassword(String username, String password){
        Client client = checkPassword(username, password);
        return client != null;
    }
}