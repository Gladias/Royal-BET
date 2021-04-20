package com.gladias.royalbet.service;


import com.gladias.royalbet.payload.BetResponse;
import com.gladias.royalbet.payload.GameResponse;
import com.gladias.royalbet.repository.BetRepository;
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
public class BetService {

    private final BetRepository repository;

    public Page<BetResponse> getAllBets(Integer page, Integer size) {
        Pageable requestPage = PageRequest.of(page, size);

        List<BetResponse> response = repository.findAll(requestPage).stream().map(
                BetResponse::of
        ).collect(Collectors.toList());

        return new PageImpl<>(response);
    }

    public Page<BetResponse> getUserBets(Long userId, Integer page, Integer size) {
        Pageable requestPage = PageRequest.of(page, size);

        List<BetResponse> response = repository.findAllByUser_id(userId, requestPage).stream().map(
                BetResponse::of
        ).collect(Collectors.toList());

        return new PageImpl<>(response);
    }
}
