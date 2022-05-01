package com.aperotech.kata.service;

import com.aperotech.kata.data.KataEntity;
import com.aperotech.kata.domain.Kata;
import com.aperotech.kata.repository.KataRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KataService {

    private final KataRepository kataRepository;

    @Autowired
    public KataService(KataRepository kataRepository, ObjectMapper objectMapper) {
        this.kataRepository = kataRepository;
    }

    public List<Kata> findAll() {
        return this.kataRepository.findAll()
                .stream()
                .map(KataEntity::toDomainKata)
                .collect(Collectors.toList());
    }


    public Kata save(Kata kata) {
        KataEntity kataEntity = new KataEntity();
        kataEntity.setName(kata.getName());
        return this.kataRepository.saveAndFlush(kataEntity).toDomainKata();
    }
}
