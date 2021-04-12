package com.gladias.royalbet.service;


import com.gladias.royalbet.model.GameEntity;
import com.gladias.royalbet.payload.GameResponse;
import com.gladias.royalbet.repository.GameRepository;
import javassist.NotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GameService {

    private final GameRepository repository;

    public Page<GameResponse> getAllGames(Integer page, Integer size) {
        Pageable requestPage = PageRequest.of(page, size);

        List<GameResponse> response = repository.findAll(requestPage).stream().map(
                GameResponse::of
        ).collect(Collectors.toList());

        return new PageImpl<>(response);
    }

    public GameResponse getGameById(@NonNull Long id) throws NotFoundException {
        GameEntity gameEntity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Game with id: " + id + " not found"));

        return GameResponse.of(gameEntity);
    }
}
