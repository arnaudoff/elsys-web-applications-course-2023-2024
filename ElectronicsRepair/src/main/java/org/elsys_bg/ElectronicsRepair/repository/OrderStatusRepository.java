package org.elsys_bg.ElectronicsRepair.repository;

import org.elsys_bg.ElectronicsRepair.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long>{
    @Query("SELECT os FROM OrderStatus os WHERE os.orderStatus = :orderStatus")
    OrderStatus getByStatus(@Param("orderStatus") String orderStatus);
}