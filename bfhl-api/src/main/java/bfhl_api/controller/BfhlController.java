package bfhl_api.controller;

import bfhl_api.dto.BfhlRequestDTO;
import bfhl_api.dto.BfhlResponseDTO;
import bfhl_api.service.BfhlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/bfhl")
@CrossOrigin(origins = "*") // Allows your front-end or testing tools to connect with no CORS issues
public class BfhlController {

    private final BfhlService bfhlService;

    // Constructor injection for best practices
    public BfhlController(BfhlService bfhlService) {
        this.bfhlService = bfhlService;
    }

    @PostMapping
    public ResponseEntity<BfhlResponseDTO> processInputData(@RequestBody BfhlRequestDTO request) {
        try {
            BfhlResponseDTO response = bfhlService.processData(request);
            if (response.isSuccess()) {
                return ResponseEntity.ok(response); // Returns HTTP 200
            } else {
                return ResponseEntity.badRequest().body(response); // Returns HTTP 400
            }
        } catch (Exception e) {
            BfhlResponseDTO errorResponse = new BfhlResponseDTO();
            errorResponse.setSuccess(false);
            return ResponseEntity.status(500).body(errorResponse); // Exception handling
        }
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> getHealthStatus() {
        Map<String, String> healthResponse = new LinkedHashMap<>();
        healthResponse.put("status", "UP");
        healthResponse.put("user_id", "Tanisha_2310992383");
        return ResponseEntity.ok(healthResponse);
    }
}