package com.aperotech.kata.repository;

import com.aperotech.kata.data.KataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KataRepository extends JpaRepository<KataEntity, Long> {
}
