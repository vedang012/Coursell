package com.vedang.coursell.controller;

import com.vedang.coursell.model.User;
import com.vedang.coursell.service.CreatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/creator")
public class CreatorController {

    private final CreatorService creatorService;

    @PostMapping("/onboard")
    public ResponseEntity<?> onboardCreator(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        creatorService.onboardCreator(user);
        return ResponseEntity.ok("Creator onboarded successfully");
    }

}
