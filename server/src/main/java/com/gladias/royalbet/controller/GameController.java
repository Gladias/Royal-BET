package com.gladias.royalbet.controller;

import com.gladias.royalbet.payload.GameResponse;
import com.gladias.royalbet.service.GameService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameService service;

    @GetMapping
    public Page<GameResponse> getAllGames(@RequestParam(defaultValue = "0") Integer page,
                                          @RequestParam(defaultValue = "20") Integer size) {

        return service.getAllGames(page, size);
    }

    @GetMapping("/{gameId}")
    public GameResponse getGameById(@PathVariable Long gameId) {
        try {
            return service.getGameById(gameId);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
