package com.game.dealornodeal.service;

import com.game.dealornodeal.model.Copper;
import com.game.dealornodeal.repository.CopperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CopperService {

    @Autowired
    private CopperRepository copperRepository;

    // CREATE
    public Copper createCopper(Copper copper) {
        return copperRepository.save(copper);
    }

    public List<Copper> createBulkCoppers(List<Copper> coppers) {
        return copperRepository.saveAll(coppers);
    }

    // READ all
    public List<Copper> getAllCoppers() {
        return copperRepository.findAll();
    }

    // READ by id
    public Optional<Copper> getCopperById(Integer id) {
        return copperRepository.findById(id);
    }

    // UPDATE
    public Optional<Copper> updateCopper(Integer id, Copper copperDetails) {
        return copperRepository.findById(id).map(copper -> {
            copper.setType(copperDetails.getType());
            copper.setDescription(copperDetails.getDescription());
            return copperRepository.save(copper);
        });
    }

    // DELETE
    public boolean deleteCopper(Integer id) {
        return copperRepository.findById(id).map(copper -> {
            copperRepository.delete(copper);
            return true;
        }).orElse(false);
    }
}
