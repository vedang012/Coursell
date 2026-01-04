package com.vedang.coursell.service;

import com.vedang.coursell.model.CreatorProfile;
import com.vedang.coursell.model.Role;
import com.vedang.coursell.model.User;
import com.vedang.coursell.repository.CreatorProfileRepo;
import com.vedang.coursell.repository.UserRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatorService {

    private final UserRepo userRepo;
    private final CreatorProfileRepo creatorProfileRepo;

    @Transactional
    public void onboardCreator(User user) {
        if (creatorProfileRepo.existsById(user.getId())) {
            throw new RuntimeException("User is already a creator");
        }

        user.setRole(Role.CREATOR);
        CreatorProfile creatorProfile = CreatorProfile.builder()
                .user(user)
                .build();

        userRepo.save(user);
        creatorProfileRepo.save(creatorProfile);
    }
}
