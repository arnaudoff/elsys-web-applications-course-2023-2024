package org.elsys_bg.ElectronicsRepair.repository;

import org.elsys_bg.ElectronicsRepair.entity.Client;
import org.elsys_bg.ElectronicsRepair.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long>{
    @Query("select c from Worker c where c.name = :username")
    Worker findByUsername(String username);

    @Query("select c from Worker c where (c.name = :username and c.password = :password)")
    Worker checkPassword(String username, String password);

    default boolean workerExists(String username){
        Worker worker = findByUsername(username);
        return worker != null;
    }

    default boolean adminExists(String username){
        Worker worker = findByUsername(username);
        return (worker != null && hasAdminRights(username));
    }

    default boolean checkWorkerPassword(String username, String password){
        Worker worker = checkPassword(username, password);
        return worker != null;
    }

    default boolean checkAdminPassword(String username, String password){
        Worker worker = checkPassword(username, password);
        return worker != null;
    }

    default boolean hasAdminRights(String username){
        String[] admins = {"avatarbg111", "admin"};

        if(Arrays.asList(admins).contains(username)){
            return true;
        }

        return false;
    }
}