package org.elsys_bg.ElectronicsRepair.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.elsys_bg.ElectronicsRepair.controller.resources.ClientResource;
import org.elsys_bg.ElectronicsRepair.controller.resources.OrderResource;
import org.elsys_bg.ElectronicsRepair.controller.resources.OrderStatusResource;
import org.elsys_bg.ElectronicsRepair.controller.resources.SupportedDeviceForRepairResource;
import org.elsys_bg.ElectronicsRepair.entity.*;
import org.elsys_bg.ElectronicsRepair.mapper.OrderMapper;
import org.elsys_bg.ElectronicsRepair.service.impl.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrderController{
    private final OrderServiceImpl orderService;
    private final OrderMapper orderMapper;
    private final OrderStatusServiceImpl orderStatusService;
    private final ClientServiceImpl clientService;
    private final DeviceTypeServiceImpl deviceTypeService;
    private final SupportedDeviceForRepairServiceImpl supportedDeviceForRepairService;

    @GetMapping("/getAll")
    public ResponseEntity<String> getAllOrders(){
        StringBuilder htmlContent = new StringBuilder();

        try{
            List<OrderResource> orders = orderService.getAllOrders();

            String joinedContent = orders.stream()
                    .map(order -> order.getId() + "---" +
                            order.getSupportedDeviceType().getDeviceType().getDeviceType() + "---" +
                            order.getSupportedDeviceType().getManufacturer() + "---" +
                            order.getModel() + "---" +
                            order.getDescription() + "---" +
                            order.getOrderStatus().getOrderStatus())
                    .collect(Collectors.joining("-;-;-"));
            htmlContent.append(joinedContent);
        }catch(Exception e){
            System.out.println(e);
            return new ResponseEntity<>("Error 500: Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(htmlContent.toString(), HttpStatus.OK);
    }

    @GetMapping("/get_unfinished_orders")
    public ResponseEntity<String> getUnfinishedOrders(){
        StringBuilder htmlContent = new StringBuilder();

        try{
            List<OrderResource> orders = orderService.getUnfinishedOrders();

            String joinedContent = orders.stream()
                    .map(order -> order.getId() + "---" +
                            order.getSupportedDeviceType().getDeviceType().getDeviceType() + "---" +
                            order.getSupportedDeviceType().getManufacturer() + "---" +
                            order.getModel() + "---" +
                            order.getDescription() + "---" +
                            order.getOrderStatus().getOrderStatus())
                    .collect(Collectors.joining("-;-;-"));
            htmlContent.append(joinedContent);
        }catch(Exception e){
            System.out.println(e);
            return new ResponseEntity<>("Error 500: Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(htmlContent.toString(), HttpStatus.OK);
    }

    @PostMapping("/add_order")
    public ResponseEntity<String> addOrder(@RequestBody String json){
        ObjectMapper objectMapper = new ObjectMapper();

        try{
            JsonNode jsonNode = objectMapper.readTree(json);

            ClientResource client = clientService.getByName(jsonNode.get("clientName").asText());
            String supportedDevice = jsonNode.get("supportedDevice").asText();
            String deviceModel = jsonNode.get("deviceModel").asText();
            String orderDescription = jsonNode.get("orderDescription").asText();

            OrderStatusResource orderStatus = orderStatusService.getByStatus("Ordered");
            String deviceManufacturer = supportedDevice.split(",")[0].trim();
            DeviceType deviceType = deviceTypeService.getByDevice(supportedDevice.split(", ")[1].trim());
            SupportedDeviceForRepairResource supportedDeviceForRepair = supportedDeviceForRepairService.getDeviceByTypeAndManufacturer(deviceManufacturer, deviceType);

            if(client == null){
                throw new RuntimeException("ERR: Given client does not exist");
            }else if(supportedDeviceForRepair == null){
                throw new RuntimeException("ERR: Device type or/and manufacturer do/es not exist");
            }else if(orderStatus == null){
                throw new RuntimeException("ERR: Device status does not exist");
            }

            OrderResource orderResource = new OrderResource();
            orderResource.setClient(client);
            orderResource.setOrderStatus(orderStatus);
            orderResource.setSupportedDeviceType(supportedDeviceForRepair);
            orderResource.setModel(deviceModel);
            orderResource.setDescription(orderDescription);

            OrderResource order = orderService.save(orderMapper.fromOrderResource(orderResource));
            if(order != null){
                return new ResponseEntity<>("ORDER_ADDED", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("ORDER_NOT_ADDED", HttpStatus.BAD_REQUEST);
            }
        }catch(Exception e){
            System.out.println("ERR: " + e.getMessage() + "\nStack Trace: " + e.getStackTrace().toString());
            return new ResponseEntity<>("Error 500: Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/finish_order")
    public ResponseEntity<String> finishOrder(@RequestBody String json){
        ObjectMapper objectMapper = new ObjectMapper();

        try{
            JsonNode jsonNode = objectMapper.readTree(json);

            OrderResource order = orderService.getById(jsonNode.get("orderId").asLong());
            order = orderService.finishOrder(orderMapper.fromOrderResource(order));
            if(order != null){
                if(order.getOrderStatus().getOrderStatus() == "Finished"){
                    return new ResponseEntity<>("ERR: Order " + jsonNode.get("orderId") + " status is already \"Finished\"", HttpStatus.BAD_REQUEST);
                }
                return new ResponseEntity<>("ORDER_FINISHED", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("ORDER_NOT_FINISHED", HttpStatus.BAD_REQUEST);
            }
        }catch(Exception e){
            System.out.println("ERR: " + e.getMessage() + "\nStack Trace: " + e.getStackTrace().toString());
            return new ResponseEntity<>("Error 500: Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}