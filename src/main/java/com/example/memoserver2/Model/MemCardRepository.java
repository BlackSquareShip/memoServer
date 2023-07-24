package com.example.memoserver2.Model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemCardRepository extends JpaRepository<MemCard, Integer> {
    List<MemCard> findAllByUsage(boolean b);
}