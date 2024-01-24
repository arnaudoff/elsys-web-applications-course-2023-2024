package org.elsys_bg.ElectronicsRepair.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.elsys_bg.ElectronicsRepair.controller.resources.SupportedDeviceForRepairResource;
import org.elsys_bg.ElectronicsRepair.entity.DeviceType;
import org.elsys_bg.ElectronicsRepair.entity.SupportedDeviceForRepair;
import org.elsys_bg.ElectronicsRepair.miscellaneous.SupportedDevicesProjection;
import org.elsys_bg.ElectronicsRepair.service.impl.DeviceTypeServiceImpl;
import org.elsys_bg.ElectronicsRepair.service.impl.SupportedDeviceForRepairServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/supported_devices")
@RequiredArgsConstructor
public class SupportedDeviceForRepairController{
    private final DeviceTypeServiceImpl deviceTypeService;
    private final SupportedDeviceForRepairServiceImpl supportedDeviceService;

    @GetMapping("/getAll")
    public ResponseEntity<String> getAllAdmins(){
        StringBuilder htmlContentBuilder = new StringBuilder();

        try{
            List<SupportedDevicesProjection> devices = supportedDeviceService.getAllDevices();
            devices.forEach(device -> {
                htmlContentBuilder.append(device.getManufacturer()).append(", ").append(device.getDeviceType()).append(";");
            });
        }catch(Exception e){
            System.out.println(e);
            return new ResponseEntity<>("Error 500: Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        String htmlContent = htmlContentBuilder.toString();
        return new ResponseEntity<>(htmlContent, HttpStatus.OK);
    }

    @PostMapping("/add_supported_device_for_repair")
    public ResponseEntity<String> addSupportedDeviceForRepair(@RequestBody String json){
        ObjectMapper objectMapper = new ObjectMapper();

        try{
            JsonNode jsonNode = objectMapper.readTree(json);
            String deviceManufacturer = jsonNode.get("deviceManufacturer").asText();
            DeviceType deviceType = deviceTypeService.getByDevice(jsonNode.get("deviceType").asText());

            if(supportedDeviceService.getDeviceByTypeAndManufacturer(deviceManufacturer, deviceType) != null){
                return new ResponseEntity<>("SUPPORTED_DEVICE_ALREADY_EXISTS", HttpStatus.OK);
            }

            SupportedDeviceForRepairResource supportedDevice = supportedDeviceService.addSupportedDevice(deviceManufacturer, deviceType);
            if(supportedDevice != null){
                return new ResponseEntity<>("SUPPORTED_DEVICE_ADDED", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("SUPPORTED_DEVICE_NOT_ADDED", HttpStatus.BAD_REQUEST);
            }
        }catch(Exception e){
            System.out.println(e);
            return new ResponseEntity<>(String.valueOf(e), HttpStatus.BAD_REQUEST);
        }
    }
}