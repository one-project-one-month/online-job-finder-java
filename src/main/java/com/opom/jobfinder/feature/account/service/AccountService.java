package com.opom.jobfinder.feature.account.service;

import org.springframework.web.multipart.MultipartFile;

public interface AccountService {

    String upload(MultipartFile file);
}
