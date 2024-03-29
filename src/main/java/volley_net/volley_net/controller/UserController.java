package volley_net.volley_net.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import volley_net.volley_net.payload.request.*;
import volley_net.volley_net.service.UserService;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
@CrossOrigin
/**
 * controller di user
 */
public class UserController {
    /**
     * servizi relativi a user {@link UserService}
     */
    private final UserService userService;

    /**
     * controller per fare l'iscrizione all'applicativo
     * @param request {@link SignupRequest}
     * @return una {@link  volley_net.volley_net.payload.response.NewUserLoginResponse} con l'utente salvato
     */
    @PostMapping("/signup") //chiamata al servizio di registrazione
    public ResponseEntity<?> save(@RequestBody @Valid SignupRequest request) { return userService.save(request);}

    /**
     * controller per fare il login nell'applicazione
     * @param request {@link  LoginRequest}
     * @return {@link  volley_net.volley_net.payload.response.LoginResponse} con l'esito del login
     */
    @PostMapping("/login") //chiamata al servizio login
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request) { return userService.login(request);}

    /**
     * controller epr ottenere uno user specifico partendo da un token
     * @param request {@link UserRequest}
     * @return {@link  volley_net.volley_net.payload.response.GetUserResponse} con l'user richiesto
     */
    @PostMapping("/getUser")
    public ResponseEntity<?> getUser(@RequestBody @Valid UserRequest request) { return userService.getUser(request);}

    /**
     * controller per cambiare il bilancio di u utente
     * @param request {@link UpdateMoneyRequest}
     * @return {@link volley_net.volley_net.payload.response.UpdateMoneyResponse} contenente il nuovo bilancio dell'utente
     */
    @PostMapping("/updateMoney")
    public ResponseEntity<?> update_money(@RequestBody @Valid UpdateMoneyRequest request){
        return userService.update_money(request);
    }

    /**
     * controller per salvare una nuova team_list
     * @param request {@link NewTeamListRequest}
     * @return {@link  volley_net.volley_net.payload.response.NewTeamListResponse} con l'esito dell'operazione di salvataggio
     */
    @PostMapping("/newTeamList")
    public ResponseEntity<?> new_team_list(@RequestBody @Valid NewTeamListRequest request){
        return userService.new_team_list(request);
    }

    /**
     * controller per avere la classifica deli user in base al count_bet
     * @return lista di {@link  volley_net.volley_net.payload.response.ListUserRankResponse} contenente gli user in ordine di count_bet
     *
     */
    @GetMapping("/getRank")
    public ResponseEntity<?> get_rank(){
        return userService.get_rank();
    }


    /**controller per avere le offerte comprate da un determinato user
     * @param request
     * @return Lista di Offerte_utente {@link  java.util.List<volley_net.volley_net.entity.Offerte_utente>}
     */

    @PostMapping("/getOfferte")
    public ResponseEntity<?> get_offerte(@RequestBody @Valid UserRequest request){
        return userService.getListOfferteUser(request);
    }


    /**
     * controller di test per salvare una nuova offerta acquistata dall'utente
     * @param request {@link SaveOffertaRequest}
     * @return ResponseEntity con l'esito del'operazione di salvataggio
     */

    @PostMapping("/saveOfferta")
    public ResponseEntity<?> save_offerte(@RequestBody @Valid SaveOffertaRequest request){
        return userService.saveOffertaUtente(request);
    }
}
