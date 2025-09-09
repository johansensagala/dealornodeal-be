package com.game.dealornodeal.repository;

import com.game.dealornodeal.model.Verse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerseRepository extends JpaRepository<Verse, Integer> {
}
