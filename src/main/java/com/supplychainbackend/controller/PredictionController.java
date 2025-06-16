package com.supplychainbackend.controller;
import com.supplychainbackend.service.PredictionService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class PredictionController {

    @Autowired
    private PredictionService predictionService;

    @PostMapping("/predict")
    public ResponseEntity<?> predictDemand(@RequestBody Map<String, Object> requestBody) {
        try {
            // Validate input
            if (requestBody == null || requestBody.isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("error", "Request body is missing or empty"));
            }

            // Extract required fields
            String category = (String) requestBody.get("category");
            String subcategory = (String) requestBody.get("subcategory");
            String region = (String) requestBody.get("region");
            String season = (String) requestBody.get("season");
            Integer warehouseId = (Integer) requestBody.get("warehouseId");
            Integer leadTime = (Integer) requestBody.get("leadTime");
            Double supplierReliability = ((Number) requestBody.get("supplierReliability")).doubleValue();
            Double price = ((Number) requestBody.get("price")).doubleValue();
            Integer stockLevel = (Integer) requestBody.get("stockLevel");
            Double transportCost = ((Number) requestBody.get("transportCost")).doubleValue();
            Integer promotion = (Integer) requestBody.get("promotion");
            Integer prevDemand1 = (Integer) requestBody.get("prevDemand1");
            Integer prevDemand2 = (Integer) requestBody.get("prevDemand2");
            Integer prevDemand3 = (Integer) requestBody.get("prevDemand3");
            Integer daysToExpiry = (Integer) requestBody.get("daysToExpiry");
            Double seasonalIndex = ((Number) requestBody.get("seasonalIndex")).doubleValue();
            Integer shelfLifeDays = (Integer) requestBody.get("shelfLifeDays");
            String manufacturingDateStr = (String) requestBody.get("manufacturingDate");
            String expiryDateStr = (String) requestBody.get("expiryDate");


            // Call prediction service
            double predictedDemand = predictionService.predict(category, subcategory, region, season, warehouseId, leadTime, supplierReliability, price, stockLevel, transportCost, promotion, prevDemand1, prevDemand2, prevDemand3,manufacturingDateStr,expiryDateStr,daysToExpiry,seasonalIndex,shelfLifeDays);

            // Return response
            return ResponseEntity.ok(Map.of("predicted_demand", predictedDemand));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Prediction failed: " + e.getMessage()));
        }
    }

}
