package com.joaovbrocchi.bancopopular.repository;

import com.joaovbrocchi.bancopopular.domain.pix.ChavePix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PixRepository extends JpaRepository<ChavePix, UUID> {
}
