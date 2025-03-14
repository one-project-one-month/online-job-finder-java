package com.opom.jobfinder.feature.account.controller;

import com.opom.jobfinder.feature.account.service.AccountService;
import com.opom.jobfinder.utility.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
@RequestMapping({"recruiter/me", "me"})
public class ProfileUploadController {

    private final AccountService accountService;

    @PostMapping("upload")
    ResponseEntity<BaseResponse> upload(@RequestParam MultipartFile file) {
        var profileImage = accountService.upload(file);
        return ResponseEntity.ok(BaseResponse.success(profileImage));
    }
}
