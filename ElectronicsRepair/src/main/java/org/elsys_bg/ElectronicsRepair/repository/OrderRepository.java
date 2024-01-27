package org.elsys_bg.ElectronicsRepair.repository;

import org.elsys_bg.ElectronicsRepair.entity.Order;
import org.elsys_bg.ElectronicsRepair.miscellaneous.OrdersProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
    @Query("SELECT o FROM Order o " +
            "JOIN FETCH o.client " +
            "JOIN FETCH o.supportedDeviceType " +
            "JOIN FETCH o.orderStatus")
    List<Order> getAllOrders();

    @Query("SELECT o FROM Order o " +
            "JOIN FETCH o.client " +
            "JOIN FETCH o.supportedDeviceType " +
            "JOIN FETCH o.orderStatus " +
            "WHERE o.orderStatus.orderStatus = 'Ordered'")
    List<Order> getUnfinishedOrders();
}