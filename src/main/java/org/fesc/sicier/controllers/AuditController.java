package org.fesc.sicier.controllers;

import lombok.RequiredArgsConstructor;
import org.fesc.sicier.persistence.entities.InformStates;
import org.fesc.sicier.persistence.entities.RequestStates;
import org.fesc.sicier.services.dtos.response.AuditResponseDto;
import org.fesc.sicier.services.dtos.response.HistoryStateResponseDto;
import org.fesc.sicier.services.interfaces.AuditService;
import org.fesc.sicier.services.interfaces.IHistoryStateService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/sicier/api/v1/audit/")
@RequiredArgsConstructor
public class AuditController {

    private final AuditService auditService;
    private final IHistoryStateService historyStateService;

    @GetMapping("operation/list")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AuditResponseDto>> getAllAudits() {
        return ResponseEntity.ok(auditService.getAll());
    }

    @GetMapping("operation/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AuditResponseDto> getAuditById(@PathVariable Long id) {
        return ResponseEntity.ok(auditService.getById(id));
    }

    @GetMapping("operation/{operation}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AuditResponseDto>> getAuditsByOperation(@PathVariable String operation) {
        return ResponseEntity.ok(auditService.getByOperation(operation));
    }

    @GetMapping("/date/{date}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AuditResponseDto>> getAuditsByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(auditService.getByDate(date));
    }

    // --- Endpoints para HistoryStateEntity ---

    @GetMapping("/states")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<HistoryStateResponseDto>> getAllHistoryStates() {
        return ResponseEntity.ok(historyStateService.getAll());
    }

    @GetMapping("/states/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HistoryStateResponseDto> getHistoryStateById(@PathVariable Long id) {
        return ResponseEntity.ok(historyStateService.getById(id));
    }

    @GetMapping("/states/inform/{informId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<HistoryStateResponseDto>> getHistoryStatesByInformId(@PathVariable Long informId) {
        return ResponseEntity.ok(historyStateService.getByInformId(informId));
    }

    @GetMapping("/states/request/{requestId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<HistoryStateResponseDto>> getHistoryStatesByRequestId(@PathVariable Long requestId) {
        return ResponseEntity.ok(historyStateService.getByRequestId(requestId));
    }

    @GetMapping("/inform/state/{state}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<HistoryStateResponseDto>> getHistoryStatesByInformState(@PathVariable InformStates state) {
        return ResponseEntity.ok(historyStateService.getByInformState(state));
    }

    @GetMapping("/request/state/{state}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<HistoryStateResponseDto>> getHistoryStatesByRequestState(@PathVariable RequestStates state) {
        return ResponseEntity.ok(historyStateService.getByRequestState(state));
    }
}
