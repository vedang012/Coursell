package com.vedang.coursell.repository;

import com.vedang.coursell.model.CreatorProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreatorProfileRepo extends JpaRepository<CreatorProfile, Long>{
}
