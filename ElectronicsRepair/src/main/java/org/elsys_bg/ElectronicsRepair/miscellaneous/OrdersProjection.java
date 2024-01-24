package org.elsys_bg.ElectronicsRepair.miscellaneous;

public interface OrdersProjection{
    Integer getId();
    ClientProjection getClient();
    SupportedDeviceForRepairProjection getSupportedDeviceType();
    String getModel();
    String getDescription();
    OrderStatusProjection getOrderStatus();
}