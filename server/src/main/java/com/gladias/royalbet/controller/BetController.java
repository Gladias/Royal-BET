package com.gladias.royalbet.controller;

import com.gladias.royalbet.payload.BetResponse;
import com.gladias.royalbet.service.BetService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/bets")
public class BetController {

    private final BetService service;

    @GetMapping
    public Page<BetResponse> getAllBets(@RequestParam(defaultValue = "0") Integer page,
                                        @RequestParam(defaultValue = "20") Integer size) {

        return service.getAllBets(page, size);
    }

    @GetMapping("/user/{userId}")
    public Page<BetResponse> getUserBets(@PathVariable Long userId,
                                         @RequestParam(defaultValue = "0") Integer page,
                                         @RequestParam(defaultValue = "20") Integer size) {
        return service.getUserBets(userId, page, size);
    }
}
