package org.elsys_bg.ElectronicsRepair.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.elsys_bg.ElectronicsRepair.controller.resources.DeviceTypeResource;
import org.elsys_bg.ElectronicsRepair.entity.DeviceType;
import org.elsys_bg.ElectronicsRepair.service.impl.DeviceTypeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/device_types")
@RequiredArgsConstructor
public class DeviceTypeController{
    private final DeviceTypeServiceImpl deviceTypeService;

    @GetMapping("/getAll")
    public ResponseEntity<String> getAllDeviceTypes(){
        StringBuilder htmlContentBuilder = new StringBuilder();

        try{
            List<DeviceTypeResource> deviceTypes = deviceTypeService.findAll();
            deviceTypes.forEach(post -> {
                htmlContentBuilder.append(post.getDeviceType()).append(";");
            });
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>("Error 500: Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        String htmlContent = htmlContentBuilder.toString();
        return new ResponseEntity<>(htmlContent, HttpStatus.OK);
    }

    @PostMapping("/add_device_type")
    public ResponseEntity<String> addDeviceType(@RequestBody String json){
        ObjectMapper objectMapper = new ObjectMapper();

        try{
            JsonNode jsonNode = objectMapper.readTree(json);
            String deviceType = jsonNode.get("deviceType").asText();

            DeviceTypeResource device = deviceTypeService.addDeviceType(deviceType);
            if(device != null){
                return new ResponseEntity<>("DEVICE_TYPE_ADDED", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("DEVICE_TYPE_NOT_ADDED", HttpStatus.BAD_REQUEST);
            }
        }catch(Exception e){
            System.out.println(e);
            return new ResponseEntity<>(String.valueOf(e), HttpStatus.BAD_REQUEST);
        }
    }
}