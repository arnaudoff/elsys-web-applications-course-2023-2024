package org.elsys_bg.ElectronicsRepair.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.elsys_bg.ElectronicsRepair.controller.resources.audit.OrderAuditResource;
import org.elsys_bg.ElectronicsRepair.service.impl.audit.OrderAuditServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/order_audit")
@RequiredArgsConstructor
public class OrderAuditController{
    private final OrderAuditServiceImpl orderAuditService;

    @GetMapping("/get_finished")
    public ResponseEntity<String> getFinishedOrders(){
        StringBuilder htmlContent = new StringBuilder();

        try{
            List<OrderAuditResource> orders = orderAuditService.getFinishedOrders();

            String joinedContent = orders.stream()
                    .map(order -> order.getId() + "---" +
                            order.getClient().getName() + "---" +
                            order.getSupportedDeviceType().getDeviceType().getDeviceType() + "---" +
                            order.getSupportedDeviceType().getManufacturer() + "---" +
                            order.getModel() + "---" +
                            order.getDescription() + "---" +
                            order.getOrderStatus().getOrderStatus() + "---" +
                            order.getAction() + "---" +
                            order.getTimestamp())
                    .collect(Collectors.joining("-;-;-"));
            htmlContent.append(joinedContent);
        }catch(Exception e){
            System.out.println(e);
            return new ResponseEntity<>("Error 500: Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(htmlContent.toString(), HttpStatus.OK);
    }

    @PostMapping("/get_orders_after_datetime")
    public ResponseEntity<String> getOrdersAfterDatetime(@RequestBody String json){
        ObjectMapper objectMapper = new ObjectMapper();
        StringBuilder htmlContent = new StringBuilder();

        try{
            JsonNode jsonNode = objectMapper.readTree(json);
            String timestampString = jsonNode.get("timestamp").asText();

            String pattern = "yyyy-MM-dd'T'HH:mm";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            LocalDateTime timestamp = LocalDateTime.parse(timestampString, formatter);

            List<OrderAuditResource> orders = orderAuditService.getOrdersAfterDatetime(timestamp);
            String joinedContent = orders.stream()
                    .map(order -> order.getId() + "---" +
                            order.getClient().getName() + "---" +
                            order.getSupportedDeviceType().getDeviceType().getDeviceType() + "---" +
                            order.getSupportedDeviceType().getManufacturer() + "---" +
                            order.getModel() + "---" +
                            order.getDescription() + "---" +
                            order.getOrderStatus().getOrderStatus() + "---" +
                            order.getAction() + "---" +
                            order.getTimestamp())
                    .collect(Collectors.joining("-;-;-"));
            htmlContent.append(joinedContent);
        }catch(Exception e){
            System.out.println(e);
            return new ResponseEntity<>("Error 500: Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(htmlContent.toString(), HttpStatus.OK);
    }
}
