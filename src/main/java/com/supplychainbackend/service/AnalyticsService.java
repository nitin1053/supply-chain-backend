package com.supplychainbackend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import java.util.Map;

@Service
public class AnalyticsService {
    private final String FLASK_URL = "http://localhost:8000"; // Flask API base URL

    private final RestTemplate restTemplate = new RestTemplate();

    public Map<String, Object> getProductPerformance() {
        String url = FLASK_URL + "/product-performance";
        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
        return response.getBody();
    }

    public Map<String, Object> getInventoryAnalytics() {
        String url = FLASK_URL + "/inventory-analytics";
        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
        return response.getBody();
    }

    public Map<String, Object> getRiskManagement() {
        String url = FLASK_URL + "/risk-management";
        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
        return response.getBody();
    }

    public Map<String, Object> getSupplierAnalysis() {
        String url = FLASK_URL + "/supplier-analysis";
        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
        return response.getBody();
    }

    public Map<String, Object> getMarketTrends() {
        String url = FLASK_URL + "/market-trends";
        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
        return response.getBody();
    }
}
