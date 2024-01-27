package org.elsys_bg.ElectronicsRepair.repository.audit;

import org.elsys_bg.ElectronicsRepair.entity.Order;
import org.elsys_bg.ElectronicsRepair.entity.audit.OrderAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderAuditRepository extends JpaRepository<OrderAudit, Long>{
    @Query("SELECT o FROM OrderAudit o " +
            "JOIN FETCH o.order " +
            "JOIN FETCH o.client " +
            "JOIN FETCH o.supportedDeviceType " +
            "JOIN FETCH o.orderStatus " +
            "WHERE o.timestamp >= :timestamp")
    List<OrderAudit> getOrdersAfterDatetime(LocalDateTime timestamp);

    @Query("SELECT o FROM OrderAudit o " +
            "JOIN FETCH o.order " +
            "JOIN FETCH o.client " +
            "JOIN FETCH o.supportedDeviceType " +
            "JOIN FETCH o.orderStatus " +
            "WHERE o.orderStatus.orderStatus = 'Finished'")
    List<OrderAudit> getFinishedOrders();
}