package com.supplychainbackend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import java.util.HashMap;
import java.util.Map;

@Service
public class PredictionService {

    private final String FLASK_URL = "http://localhost:8000/predict"; // ML API URL

    public double predict(String category, String subcategory, String region, String season,
                          int warehouseId, int leadTime, double supplierReliability, double price,
                          int stockLevel, double transportCost, int promotion,
                          int prevDemand1, int prevDemand2, int prevDemand3,String manufacturingDate, String expiryDate,
                          int daysToExpiry, double seasonalIndex, int shelfLifeDays) {

        // Prepare input JSON data
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("category", category);
        requestBody.put("subcategory", subcategory);
        requestBody.put("region", region);
        requestBody.put("season", season);
        requestBody.put("warehouseId", warehouseId);
        requestBody.put("leadTime", leadTime);
        requestBody.put("supplierReliability", supplierReliability);
        requestBody.put("price", price);
        requestBody.put("stockLevel", stockLevel);
        requestBody.put("transportCost", transportCost);
        requestBody.put("promotion", promotion);
        requestBody.put("prevDemand1", prevDemand1);
        requestBody.put("prevDemand2", prevDemand2);
        requestBody.put("prevDemand3", prevDemand3);
        requestBody.put("daysToExpiry", daysToExpiry);
        requestBody.put("seasonalIndex", seasonalIndex);
        requestBody.put("shelfLifeDays", shelfLifeDays);
        requestBody.put("manufacturingDate", manufacturingDate);
        requestBody.put("expiryDate", expiryDate);


        // Set headers for JSON request
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        // Make API request to Flask
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(FLASK_URL, entity, Map.class);

        // Extract prediction from response
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return (double) response.getBody().get("predicted_demand");
        } else {
            throw new RuntimeException("ML API response error: " + response.getStatusCode());
        }
    }
}
