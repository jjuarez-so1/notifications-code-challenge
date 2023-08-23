package com.jjuarez.gila.controller;

import com.jjuarez.gila.dto.KPIsDTO;
import com.jjuarez.gila.service.KpiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(KpiController.BASE_PATH)
public class KpiController {
    private static final Logger LOG = LoggerFactory.getLogger(KpiController.class);
    public static final String BASE_PATH = "/api/kpis";
    private final KpiService kpiService;

    public KpiController(KpiService kpiService) {
        this.kpiService = kpiService;
    }

    @GetMapping()
    public ResponseEntity<KPIsDTO> getKpis() {
        LOG.info("Requesting KPIs for last notification sent");
        KPIsDTO returnObject = kpiService.getKpisForLastNotification();
        return ResponseEntity.ok(returnObject);
    }
}
