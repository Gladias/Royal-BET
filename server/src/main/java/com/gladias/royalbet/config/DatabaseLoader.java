package com.gladias.royalbet.config;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.gladias.royalbet.model.GameEntity;
import com.gladias.royalbet.model.OddsEntity;
import com.gladias.royalbet.repository.GameRepository;
import com.gladias.royalbet.repository.OddsRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class DatabaseLoader implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(DatabaseLoader.class);
    private final GameRepository gameRepository;
    private final OddsRepository oddsRepository;

    @Override
    public void run(String... args) throws Exception {

    }

    /*
    @Override
    public void run(String... args) throws Exception {
        int numberOfDaysAhead = 3;
        LocalDateTime day = LocalDateTime.now();
        DateTimeFormatter requestFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (int i = 0; i < numberOfDaysAhead; i++) {
            day = day.plusDays(1);
            String requestDate = day.format(requestFormatter);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api-nba-v1.p.rapidapi.com/games/date/" + requestDate))
                    .header("x-rapidapi-key", "")
                    .header("x-rapidapi-host", "api-nba-v1.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            //String body = "{\"api\":{\"status\":200,\"message\":\"GET games\\/date\\/2021-06-12\",\"results\":1,\"filters\":[\"seasonYear\",\"league\",\"gameId\",\"teamId\",\"date\",\"live\"],\"games\":[{\"seasonYear\":\"2020\",\"league\":\"standard\",\"gameId\":\"9419\",\"startTimeUTC\":\"2021-06-12T02:00:00.000Z\",\"endTimeUTC\":\"\",\"arena\":\"\",\"city\":\"\",\"country\":\"\",\"clock\":\"\",\"gameDuration\":\"\",\"currentPeriod\":\"0\\/4\",\"halftime\":\"\",\"EndOfPeriod\":\"\",\"seasonStage\":\"4\",\"statusShortGame\":\"1\",\"statusGame\":\"Scheduled\",\"vTeam\":{\"teamId\":\"28\",\"shortName\":\"PHX\",\"fullName\":\"Phoenix Suns\",\"nickName\":\"Suns\",\"logo\":\"https:\\/\\/upload.wikimedia.org\\/wikipedia\\/fr\\/5\\/56\\/Phoenix_Suns_2013.png\",\"score\":{\"points\":\"\"}},\"hTeam\":{\"teamId\":\"9\",\"shortName\":\"DEN\",\"fullName\":\"Denver Nuggets\",\"nickName\":\"Nuggets\",\"logo\":\"https:\\/\\/upload.wikimedia.org\\/wikipedia\\/fr\\/thumb\\/3\\/35\\/Nuggets_de_Denver_2018.png\\/180px-Nuggets_de_Denver_2018.png\",\"score\":{\"points\":\"\"}}}]}}";
            JSONObject json = new JSONObject(response.body());

            JSONArray array = json.getJSONObject("api").getJSONArray("games");

            for (int j = 0; j < array.length(); j++) {
                var game = array.getJSONObject(j);

                String hostTeam = game.getJSONObject("hTeam").getString("fullName");
                String visitorsTeam = game.getJSONObject("vTeam").getString("fullName");

                DateTimeFormatter responseFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
                LocalDateTime time = LocalDateTime.parse(game.getString("startTimeUTC"), responseFormatter);

                Random random = new Random();

                int coinFlip = random.nextInt(2);

                double hostWinOdds, visitorsWinOdds, tieOdds = Math.round(random.nextDouble() + 1.25 * 100) / 100.0;

                if (coinFlip == 1) {
                    hostWinOdds = Math.round(random.nextDouble() + 2.5 * 100) / 100.0;
                    visitorsWinOdds = Math.round(random.nextDouble() + 1.0 * 100) / 100.0;
                } else {
                    hostWinOdds = Math.round((random.nextDouble() + 1.0) * 100) / 100.0;
                    visitorsWinOdds = Math.round(random.nextDouble() + 2.5 * 100) / 100.0;
                }

                OddsEntity odds = new OddsEntity(hostWinOdds, tieOdds, visitorsWinOdds);

                oddsRepository.save(odds);

                gameRepository.save(GameEntity.builder()
                        .hostTeam(hostTeam)
                        .visitorsTeam(visitorsTeam)
                        .time(time)
                        .odds(odds)
                        .build());
            }

            LOG.info("Games from day " + requestDate + " have been fetched successfully");
        }
    }
    */
}
