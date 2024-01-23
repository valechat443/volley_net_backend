package volley_net.volley_net.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import volley_net.volley_net.payload.request.GameGenericRequest;
import volley_net.volley_net.payload.request.GameSpecificRequest;
import volley_net.volley_net.payload.request.SaveGameRequest;
import volley_net.volley_net.payload.request.WeekMaxRequest;
import volley_net.volley_net.service.GameService;

@RestController
@RequestMapping("game")
@RequiredArgsConstructor
@CrossOrigin
/**
 * controller di game
 */
public class GameController {

    /**
     * servizi relativi a game {@link GameService}
     */
    private final GameService gameService;

    /**
     * controller per test, per avere un game specifico
     * @param request {@link GameSpecificRequest}
     * @return  un oggetto {@link volley_net.volley_net.entity.Game}
     */
    @PostMapping("/getGame")
    public ResponseEntity<?> test_get_game(@RequestBody @Valid GameSpecificRequest request){
        return gameService.getGameById(request);
    }

    /**
     * controller per avere la giornata massima di uan lega
     * @param request {@link WeekMaxRequest}
     * @return {@link volley_net.volley_net.payload.response.GetWeekMaxResponse} contenente l'ultima giornata disponibile
     */
    @PostMapping("/maxWeek")
    public ResponseEntity<?> max_week(@RequestBody @Valid WeekMaxRequest request){
        return gameService.get_week_max(request);
    }

    /**
     * controller per avere una lista di game di una lega, di una stagione
     * @param request {@link  GameGenericRequest}
     * @return lista di {@link  volley_net.volley_net.payload.response.GetGameGenericResponse} contenete una serie di partite
     */
    @PostMapping("/getGeneric")
    public ResponseEntity<?> get_generic(@RequestBody @Valid GameGenericRequest request){
        return gameService.get_game_generic(request);
    }

    /**
     * controller per avere una lista di game che avverano tra due giorni su cui scommettere
     * @return get {@link java.util.List<volley_net.volley_net.entity.Bet>} per i due giorni successivi a oggi
     */
    @GetMapping("/betPage")
    public ResponseEntity<?> get_bet_page(){

        return gameService.bets_page();
    }


    /**
     * controller per salvare una partita (game)
     * @param request {@link SaveGameRequest}
     * @return messaggio per indicare se il salvataggio è andato a buon fine
     */

    @PostMapping ("/save")
    public ResponseEntity<?> salva_game(@RequestBody @Valid SaveGameRequest request){

        return gameService.salva_partita_completa(request);
    }


    /**
     * controller per avere l'ultima partita disponibile della super lega
     * @return ultimo {@link volley_net.volley_net.entity.Game} giocato di superlega
     */

    @GetMapping("/default")
    public ResponseEntity<?> default_game(){
        return gameService.get_default_game();
    }

    /**
     * controller per avere un game specifico
     * @param request {@link GameSpecificRequest}
     * @return {@link  volley_net.volley_net.payload.response.GetGameSpecificResponse} con i dati della partita
     */
    @PostMapping("/getSpecific")
    public ResponseEntity<?> get_game_specific(@RequestBody @Valid GameSpecificRequest request){
        return gameService.get_game_specific(request);
    }
}
