package volley_net.volley_net.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import volley_net.volley_net.entity.*;
import volley_net.volley_net.repository.LeagueRepository;
import volley_net.volley_net.repository.StatisticRepository;
import volley_net.volley_net.repository.TeamSeasonRepository;

import java.time.LocalDate;
import java.util.Collections;

@Service
@Slf4j
public class SheduleService {
    private final TeamSeasonRepository teamSeasonRepository;
    private final StatisticRepository statisticRepository;
    private final LeagueRepository leagueRepository;

    public SheduleService(TeamSeasonRepository teamSeasonRepository, StatisticRepository statisticRepository, LeagueRepository leagueRepository) {
        this.teamSeasonRepository = teamSeasonRepository;
        this.statisticRepository = statisticRepository;
        this.leagueRepository = leagueRepository;
    }

    /**
     * controllo se un utente ha vinto una sua bet di un determinato game
     */
    private ResponseEntity<?> check_bet() {
        return null;
    }

    /**
     * aggiorno le classifiche di tutte le leghe della stagione corrente
     */
    private ResponseEntity<?> update_standings() {
        return null;
    }

    /**
     * aggiorna gli esiti e i punteggi di tutte le partite finite
     */
    //@Scheduled(cron = "0 3 16 * * ?")
    public void update_games() {
        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.add("x-rapidapi-key", "511b2d0f52b5dfe6b5964d0801a4b155");
            headers.add("x-rapidapi-host", "v1.volleyball.api-sports.io");

            HttpEntity<String> entity = new HttpEntity<String>(headers);

            ResponseEntity<String> cose = restTemplate.exchange(
                    "https://v1.volleyball.api-sports.io/games?date=" + LocalDate.now().toString(),
                    HttpMethod.GET,
                    entity,
                    String.class);

            JSONObject j = new JSONObject(cose.getBody());
            JSONArray response = j.getJSONArray("response");

            for (int i = 0; i < response.length(); i++) {
                JSONObject game = response.getJSONObject(i);
                if (game.getJSONObject("country").getString("code").equals("IT")) {
                    log.info(game.toString());
                }
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * aggiorno le statistiche di un team di una lega di una stagione
     */
    @Scheduled(cron = "0 51 17 * * ?")
    private ResponseEntity<?> get_team_statistic() {
        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.add("x-rapidapi-key", "511b2d0f52b5dfe6b5964d0801a4b155");
            headers.add("x-rapidapi-host", "v1.volleyball.api-sports.io");

            HttpEntity<String> entity = new HttpEntity<String>(headers);


            ResponseEntity<String> cose = restTemplate.exchange(
                    "https://v1.volleyball.api-sports.io/teams/statistics?league=97&season=2023&team=571",
                    HttpMethod.GET,
                    entity,
                    String.class);

            JSONObject j = new JSONObject(cose.getBody());

            // La stagione è un campo separato nell'oggetto JSON principale
            int season = j.getJSONObject("parameters").getInt("season");

            // Ottenere i dettagli del team
            JSONObject team = j.getJSONObject("response").getJSONObject("team");
            int teamId = team.getInt("id");
            String teamName = team.getString("name");
            String teamLogo = team.getString("logo");

            // Ottenere i dettagli della lega
            JSONObject league = j.getJSONObject("response").getJSONObject("league");
            int leagueId = league.getInt("id");
            String leagueName = league.getString("name");
            String leagueLogo = league.getString("logo");

            // Ottenere i dettagli del paese
            JSONObject country = j.getJSONObject("response").getJSONObject("country");
            int countryId = country.getInt("id");
            String countryName = country.getString("name");
            String countryFlag = country.getString("flag");

            // Ottenere i dettagli dei giochi
            JSONObject games = j.getJSONObject("response").getJSONObject("games");

            JSONObject played = games.getJSONObject("played");
            int homePlayed = played.getInt("home");
            int awayPlayed = played.getInt("away");
            int allPlayed = played.getInt("all");

            // Ottenere i dettagli degi punti
            JSONObject goals = j.getJSONObject("response").getJSONObject("goals");

            // Ottenere i dettagli delle vittorie
            JSONObject wins = games.getJSONObject("wins");
            int homeWinsTotal = wins.getJSONObject("home").getInt("total");
            int awayWinsTotal = wins.getJSONObject("away").getInt("total");

            // Ottenere i dettagli dei pareggi
            JSONObject draws = games.getJSONObject("draws");
            int homeDrawsTotal = draws.getJSONObject("home").getInt("total");
            int awayDrawsTotal = draws.getJSONObject("away").getInt("total");

            // Ottenere i dettagli delle sconfitte
            JSONObject loses = games.getJSONObject("loses");
            int homeLosesTotal = loses.getJSONObject("home").getInt("total");
            int awayLosesTotal = loses.getJSONObject("away").getInt("total");

            //Punti fatti in casa e fuori
            JSONObject forGoals = goals.getJSONObject("for");
            JSONObject totalForGoals = forGoals.getJSONObject("total");
            int homeTotalForGoals = totalForGoals.getInt("home");
            int awayTotalForGoals = totalForGoals.getInt("away");

            //Punti subiti in casa e fuori
            JSONObject againstGoals = goals.getJSONObject("against");
            JSONObject totalaAgainstGoals = forGoals.getJSONObject("total");
            int homeTotalAgainstGoals = totalForGoals.getInt("home");
            int awayTotalAgainstGoals = totalForGoals.getInt("away");
            boolean national=false;

            // Stampa dei dettagli
            System.out.println("Team ID: " + teamId);
            System.out.println("Team Name: " + teamName);
            System.out.println("Team Logo: " + teamLogo);
            System.out.println("League ID: " + leagueId);
            System.out.println("Country ID: " + countryId);
            System.out.println("Country Name: " + countryName);
            System.out.println("Country Flag: " + countryFlag);
            System.out.println("Home Played: " + homePlayed);
            System.out.println("Away Played: " + awayPlayed);
            System.out.println("All Played: " + allPlayed);
            System.out.println("Home Wins Total: " + homeWinsTotal);
            System.out.println("Away Wins Total: " + awayWinsTotal);
            System.out.println("Home Draws Total: " + homeDrawsTotal);
            System.out.println("Away Draws Total: " + awayDrawsTotal);
            System.out.println("Home Loses Total: " + homeLosesTotal);
            System.out.println("Away Loses Total: " + awayLosesTotal);
            System.out.println("Home Total For Goals: " + homeTotalForGoals);
            System.out.println("Away Total For Goals: " + awayTotalForGoals);
            System.out.println("Home Total Against Goals: " + homeTotalAgainstGoals);
            System.out.println("Away Total Against Goals: " + awayTotalAgainstGoals);

            Team_season team_season = new Team_season(new League(leagueId),new Season(season),new Team(teamId));
            Statistic statistic = new Statistic(team_season,homePlayed,awayPlayed,homeWinsTotal,awayWinsTotal,homeLosesTotal,awayLosesTotal,homeDrawsTotal,awayDrawsTotal,homeTotalForGoals,awayTotalForGoals,homeTotalAgainstGoals,awayTotalAgainstGoals);
            statisticRepository.save(statistic);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
