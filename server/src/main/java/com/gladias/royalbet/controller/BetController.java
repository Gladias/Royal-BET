package com.gladias.royalbet.controller;

import com.gladias.royalbet.payload.BetResponse;
import com.gladias.royalbet.payload.BetsRequest;
import com.gladias.royalbet.payload.SingleBet;
import com.gladias.royalbet.service.BetService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import static com.gladias.royalbet.service.UserService.getUsernameFromToken;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/bets")
public class BetController {

    private final BetService service;

    @GetMapping("/all")
    public Page<BetResponse> getAllBets(@RequestParam(defaultValue = "0") Integer page,
                                        @RequestParam(defaultValue = "20") Integer size) {

        return service.getAllBets(page, size);
    }

    @GetMapping
    public Page<BetResponse> getUserBets(@CookieValue("token") String token,
                                         @RequestParam(defaultValue = "0") Integer page,
                                         @RequestParam(defaultValue = "20") Integer size) {

        return service.getUserBets(getUsernameFromToken(token), page, size);
    }

    @PostMapping
    public void addBets(@CookieValue("token") String token, @RequestBody BetsRequest bets) {
        String userName = getUsernameFromToken(token);

        for (SingleBet singleBet : bets.getBets()) {
            service.addBet(userName, singleBet);
        }
    }
}
