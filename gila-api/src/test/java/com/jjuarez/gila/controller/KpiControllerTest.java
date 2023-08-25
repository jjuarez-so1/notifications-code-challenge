package com.jjuarez.gila.controller;

import com.jjuarez.gila.dto.KPITypeDTO;
import com.jjuarez.gila.dto.KPIsDTO;
import com.jjuarez.gila.service.KpiService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KpiControllerTest {

    @Mock
    private KpiService kpiService;

    @InjectMocks
    private KpiController kpiController;

    @Test
    void testGetKpis() {
        final KPIsDTO expectedKPIs = new KPIsDTO();
        final KPITypeDTO usersKPI = new KPITypeDTO("USERS", 1);
        expectedKPIs.setUsers(usersKPI);

        when(kpiService.getKpisForLastNotification()).thenReturn(expectedKPIs);

        ResponseEntity<KPIsDTO> actualResponseEntity = kpiController.getKpis();

        assertEquals(expectedKPIs, actualResponseEntity.getBody());
        assertEquals(HttpStatus.OK, actualResponseEntity.getStatusCode());

        verify(kpiService).getKpisForLastNotification();
    }
}