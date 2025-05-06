package com.opom.jobfinder.feature.admin.account.controller;

import com.opom.jobfinder.feature.admin.account.service.AccountService;
import com.opom.jobfinder.utility.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/admin/accounts")
@RequiredArgsConstructor
public class AccountController {

    private AccountService accountService;

    @PostMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(BaseResponse.success(accountService.updateAccount(id)));
    }
}
