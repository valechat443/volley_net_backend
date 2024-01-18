package volley_net.volley_net.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import volley_net.volley_net.payload.request.BetFutureRequest;
import volley_net.volley_net.payload.request.CreateBetRequest;
import volley_net.volley_net.payload.request.UserRequest;
import volley_net.volley_net.service.BetService;


@RestController
@RequestMapping("bet")
@RequiredArgsConstructor
@CrossOrigin
/**
 * controller per bet
 */
public class BetController {
    /**
     * servizi relativi a di bet {@link BetService}
     *
     */
    private  final BetService betService;

    /**
     * controller per salvare una nuova bet
     * @param request {@link CreateBetRequest}
     * @return  ResponseEntity con l'esito dell'operazione di salvataggio
     */
    @PostMapping("/save")
    public ResponseEntity<?> create_bet(@RequestBody @Valid CreateBetRequest request){
        return betService.salva_bet(request);
    }

    /**
     * controller per avere una lista di bet future
     * @param request {@link  BetFutureRequest}
     * @return lista di {@link volley_net.volley_net.payload.response.BetPageResponse} contenente le scommesse
     */
    @PostMapping("/betList")
    public  ResponseEntity<?> get_future_bet(@RequestBody @Valid BetFutureRequest request){
        return betService.get_future_bets(request);
    }

    /**
     * controller per avere la lista delle scommesse fate da un utente
     * @param request {@link UserRequest}
     * @return  ResponseEntity con la lista delle scommesse fatte dall'utente {@link  java.util.List<volley_net.volley_net.entity.Bet>}
     */
    @PostMapping("/betUtente")
    public ResponseEntity<?> get_bet_utente(@RequestBody @Valid UserRequest request){
        return betService.get_bets_user(request);
    }
}
