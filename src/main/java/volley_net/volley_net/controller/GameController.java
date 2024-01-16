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

    private final GameService gameService;

    /**
     * controller per test, per avere un game specifico
     * @param request
     * @return un oggetto game
     */
    @PostMapping("/getGame")
    public ResponseEntity<?> test_get_game(@RequestBody @Valid GameSpecificRequest request){
        return gameService.getGameById(request);
    }

    /**
     * controller per avere la giornata massima di uan lega
     * @param request
     * @return GetWeekMaxResponse contenente l'ultima giornata disponibile
     */
    @PostMapping("/maxWeek")
    public ResponseEntity<?> max_week(@RequestBody @Valid WeekMaxRequest request){
        return gameService.get_week_max(request);
    }

    /**
     * controller per avere una lista di game di una lega, di una stagione
     * @param request
     * @return lista di GetGameGenericResponse contenete una serie di partite
     */
    @PostMapping("/getGeneric")
    public ResponseEntity<?> get_generic(@RequestBody @Valid GameGenericRequest request){
        return gameService.get_game_generic(request);
    }

    /**
     * controller per avere una lista di game che avverano tra 2 giorni su cui scommettere
     * @return get scommesse per i due giorni successivi a ogg
     */
    @GetMapping("/betPage")
    public ResponseEntity<?> get_bet_page(){

        return gameService.bets_page();
    }


    /**
     * controller per salvare una partita (game)
     * @param request
     * @return messaggio per indicare se il salvataggio è andato a buon fine
     */

    @PostMapping ("/save")
    public ResponseEntity<?> salva_game(@RequestBody @Valid SaveGameRequest request){

        return gameService.salva_partita_completa(request);
    }


    /**
     * controller per avere l'ultima partita disponibile della super lega
     * @return ultimo game giocato di superlega
     */

    @GetMapping("/default")
    public ResponseEntity<?> default_game(){
        return gameService.get_default_game();
    }

    /**
     * controller per avere un game specifico
     * @param request
     * @return GetGameSpecificResponse con i dati della partita
     */
    @PostMapping("/getSpecific")
    public ResponseEntity<?> get_game_specific(@RequestBody @Valid GameSpecificRequest request){
        return gameService.get_game_specific(request);
    }
}
