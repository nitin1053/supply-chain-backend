package com.supplychainbackend.controller;

import com.supplychainbackend.service.AnalyticsService;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {
    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/product-performance")
    public Map<String, Object> getProductPerformance() {
        return analyticsService.getProductPerformance();
    }

    @GetMapping("/inventory-analytics")
    public Map<String, Object> getInventoryAnalytics() {
        return analyticsService.getInventoryAnalytics();
    }

    @GetMapping("/risk-management")
    public Map<String, Object> getRiskManagement() {
        return analyticsService.getRiskManagement();
    }

    @GetMapping("/supplier-analysis")
    public Map<String, Object> getSupplierAnalysis() {
        return analyticsService.getSupplierAnalysis();
    }

    @GetMapping("/market-trends")
    public Map<String, Object> getMarketTrends() {
        return analyticsService.getMarketTrends();
    }
}
