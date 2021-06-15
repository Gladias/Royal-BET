package com.gladias.royalbet.service;


import com.gladias.royalbet.model.BetEntity;
import com.gladias.royalbet.model.BetStatusEntity;
import com.gladias.royalbet.model.GameEntity;
import com.gladias.royalbet.model.UserEntity;
import com.gladias.royalbet.payload.BetResponse;
import com.gladias.royalbet.payload.GameResponse;
import com.gladias.royalbet.payload.SingleBet;
import com.gladias.royalbet.repository.BetRepository;
import com.gladias.royalbet.repository.BetStatusRepository;
import com.gladias.royalbet.repository.GameRepository;
import com.gladias.royalbet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BetService {

    private final BetRepository betRepository;
    private final GameRepository gameRepository;
    private final UserRepository userRepository;
    private final BetStatusRepository betStatusRepository;

    public Page<BetResponse> getAllBets(Integer page, Integer size) {
        Pageable requestPage = PageRequest.of(page, size);

        List<BetResponse> response = betRepository.findAll(requestPage).stream().map(
                BetResponse::of
        ).collect(Collectors.toList());

        return new PageImpl<>(response);
    }

    public Page<BetResponse> getUserBets(String userLogin, Integer page, Integer size) {
        Pageable requestPage = PageRequest.of(page, size);

        List<BetResponse> response = betRepository.findAllByUserLogin(userLogin, requestPage).stream().map(
                BetResponse::of
        ).collect(Collectors.toList());

        return new PageImpl<>(response);
    }

    public void addBet(String userLogin, SingleBet requestBet) {
        UserEntity user = userRepository.findByLogin(userLogin).get();
        GameEntity game = gameRepository.findById(requestBet.getGameId()).get();
        BetStatusEntity betStatusEntity = new BetStatusEntity("Ongoing");

        BetEntity bet = BetEntity.builder()
                .placedAt(LocalDateTime.now())
                .stake(requestBet.getStake())
                .winner(requestBet.getWinner())
                .game(game)
                .user(user)
                .status(betStatusEntity)
                .build();

        user.setMoney(user.getMoney() - requestBet.getStake());
        userRepository.save(user);
        betStatusRepository.save(betStatusEntity);
        betRepository.save(bet);
    }
}
