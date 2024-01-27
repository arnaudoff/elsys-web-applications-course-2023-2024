package org.elsys_bg.ElectronicsRepair.repository;

import jakarta.transaction.Transactional;
import org.elsys_bg.ElectronicsRepair.entity.Client;
import org.elsys_bg.ElectronicsRepair.entity.ClientContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientContactRepository extends JpaRepository<ClientContact, Long>{
    @Transactional
    @Modifying
    @Query("DELETE FROM ClientContact u WHERE u.client = :client")
    void deleteByClient(@Param("client") Client client);
}