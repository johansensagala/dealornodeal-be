package com.game.dealornodeal.service;

import com.game.dealornodeal.model.Verse;
import com.game.dealornodeal.repository.VerseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VerseService {
    @Autowired
    private VerseRepository verseRepository;

    // CREATE
    public Verse createVerse(Verse verse) {
        return verseRepository.save(verse);
    }

    // BULK CREATE
    public List<Verse> createBulkVerses(List<Verse> verses) {
        return verseRepository.saveAll(verses);
    }

    // READ all
    public List<Verse> getAllVerses() {
        return verseRepository.findAll();
    }

    // READ by id
    public Optional<Verse> getVerseById(Integer id) {
        return verseRepository.findById(id);
    }

    // READ random
    public Optional<Verse> getRandomVerse() {
        List<Verse> verses = verseRepository.findAll();
        if (verses.isEmpty()) {
            return Optional.empty();
        }
        int randomIndex = (int) (Math.random() * verses.size());
        return Optional.of(verses.get(randomIndex));
    }

    // UPDATE
    public Optional<Verse> updateVerse(Integer id, Verse verseDetails) {
        return verseRepository.findById(id).map(verse -> {
            verse.setReference(verseDetails.getReference());
            verse.setContent(verseDetails.getContent());
            return verseRepository.save(verse);
        });
    }

    // DELETE
    public boolean deleteVerse(Integer id) {
        return verseRepository.findById(id).map(verse -> {
            verseRepository.delete(verse);
            return true;
        }).orElse(false);
    }
}
