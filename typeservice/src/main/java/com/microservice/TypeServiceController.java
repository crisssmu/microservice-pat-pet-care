package com.microservice;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.service.TypeServiceService;

@RestController
@RequestMapping("api/typeService")
public class TypeServiceController {

    @Autowired
    private TypeServiceService tsService;

    @PostMapping("/register")
    public ResponseEntity<HashMap<String, Object>> registerTypeService(TypeService typeService) {
        HashMap<String, Object> response = new HashMap<>();
        if(typeService != null){
            tsService.registerTypeService(typeService);
            response.put("message", "TypeService registered successfully");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "TypeService not registered");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/getAll")
    public ResponseEntity<HashMap<String, Object>> getAllTypeServices() {
        HashMap<String, Object> response = new HashMap<>();
        if (tsService.getAllTypeServices().isEmpty()) {
            response.put("message", "No TypeServices found");
            return ResponseEntity.badRequest().body(response);
        }
        response.put("typeServices", tsService.getAllTypeServices());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/getTypeServiceByCode")
    public ResponseEntity<HashMap<String, Object>> getTypeServiceByCode(String name) {
        HashMap<String, Object> response = new HashMap<>();
        long id = tsService.getTypeServiceByCode(name);
        if (id == -1) {
            response.put("message", "TypeService not found");
            return ResponseEntity.badRequest().body(response);
        }
        response.put("typeService", tsService.getTypeServiceByCode(name));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/delete")
    public ResponseEntity<HashMap<String, Object>> deleteTypeService(String name) {

        HashMap<String, Object> response = new HashMap<>();
        long id = tsService.getTypeServiceByCode(name);
        if(id == -1){
            response.put("message", "TypeService not found");
            return ResponseEntity.badRequest().body(response);
        }
        tsService.deleteTypeService(id);
        response.put("message", "TypeService deleted successfully");
        return ResponseEntity.ok(response);
    }

}
