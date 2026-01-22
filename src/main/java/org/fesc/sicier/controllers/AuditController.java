package org.fesc.sicier.controllers;

import lombok.RequiredArgsConstructor;
import org.fesc.sicier.services.dtos.response.AuditResponseDto;
import org.fesc.sicier.services.interfaces.AuditService;
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

    @GetMapping("list")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AuditResponseDto>> getAllAudits() {
        return ResponseEntity.ok(auditService.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AuditResponseDto> getAuditById(@PathVariable Long id) {
        return ResponseEntity.ok(auditService.getById(id));
    }

    @GetMapping("/operation/{operation}")
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
}
