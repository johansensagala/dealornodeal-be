package com.game.dealornodeal.service;

import com.game.dealornodeal.dto.request.CopperRequest;
import com.game.dealornodeal.dto.request.GameRequest;
import com.game.dealornodeal.dto.response.CopperResponse;
import com.game.dealornodeal.dto.response.GameResponse;
import com.game.dealornodeal.model.Copper;
import com.game.dealornodeal.model.Game;
import com.game.dealornodeal.repository.CopperRepository;
import com.game.dealornodeal.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private CopperRepository copperRepository;

    @Transactional
    public GameResponse createGame(GameRequest request) {
        validateCopperCount(request.getCoppers());

        Set<Copper> coppers = resolveCoppers(request.getCoppers());

        Game game = new Game();
        game.setName(request.getName());
        game.setDescription(request.getDescription());
        game.setCoppers(coppers);

        Game saved = gameRepository.save(game);
        return mapToResponse(saved);
    }

    @Transactional
    public GameResponse updateGame(Integer id, GameRequest request) {
        return gameRepository.findById(id).map(game -> {
            validateCopperCount(request.getCoppers());
            Set<Copper> coppers = resolveCoppers(request.getCoppers());

            game.setName(request.getName());
            game.setDescription(request.getDescription());
            game.setCoppers(coppers);

            Game saved = gameRepository.save(game);
            return mapToResponse(saved);
        }).orElseThrow(() -> new RuntimeException("Game not found with id " + id));
    }

    public List<GameResponse> getAllGames() {
        return gameRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public GameResponse getGameById(Integer id) {
        return gameRepository.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new RuntimeException("Game not found with id " + id));
    }

    public void deleteGame(Integer id) {
        if (!gameRepository.existsById(id)) {
            throw new RuntimeException("Game not found with id " + id);
        }
        gameRepository.deleteById(id);
    }

    // ================= Helpers =================

    private void validateCopperCount(List<CopperRequest> copperRequests) {
        if (copperRequests == null || copperRequests.size() != 40) {
            throw new RuntimeException("A game must have exactly 40 coppers");
        }
    }

    private Set<Copper> resolveCoppers(List<CopperRequest> requests) {
        Set<Copper> result = new HashSet<>();

        for (CopperRequest req : requests) {
            if (req.getId() != null) {
                // pakai copper existing
                Copper existing = copperRepository.findById(req.getId())
                        .orElseThrow(() -> new RuntimeException("Copper not found with id " + req.getId()));
                result.add(existing);
            } else {
                // buat copper baru
                if (req.getType() == null || req.getDescription() == null) {
                    throw new RuntimeException("New copper must have type and description");
                }
                Copper newCopper = new Copper();
                newCopper.setType(req.getType());
                newCopper.setDescription(req.getDescription());
                result.add(copperRepository.save(newCopper));
            }
        }

        return result;
    }

    private GameResponse mapToResponse(Game game) {
        GameResponse response = new GameResponse();
        response.setId(game.getId());
        response.setName(game.getName());
        response.setDescription(game.getDescription());
        response.setCoppers(
                game.getCoppers().stream().map(c -> {
                    CopperResponse cr = new CopperResponse();
                    cr.setId(c.getId());
                    cr.setType(c.getType());
                    cr.setDescription(c.getDescription());
                    return cr;
                }).collect(Collectors.toList())
        );
        return response;
    }
}
