package com.game.dealornodeal.repository;

import com.game.dealornodeal.model.Copper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CopperRepository extends JpaRepository<Copper, Integer> {
}
