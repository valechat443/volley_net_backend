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
public class UserController {

    private final UserService userService;

    @PostMapping("/signup") //chiamata al servizio di registrazione
    public ResponseEntity<?> save(@RequestBody @Valid SignupRequest request) { return userService.save(request);}

    @PostMapping("/login") //chiamata al servizio login
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request) { return userService.login(request);}

    @PostMapping("/getUser")
    public ResponseEntity<?> getUser(@RequestBody @Valid UserRequest request) { return userService.getUser(request);}

    @PostMapping("/updateMoney")
    public ResponseEntity<?> update_money(@RequestBody @Valid UpdateMoneyRequest request){
        return userService.update_money(request);
    }

    @PostMapping("/newTeamList")
    public ResponseEntity<?> new_team_list(@RequestBody @Valid NewTeamListRequest request){
        return userService.new_team_list(request);
    }

    @GetMapping("/getRank")
    public ResponseEntity<?> get_rank(){
        return userService.get_rank();
    }

    @PostMapping("/getOfferte")
    public ResponseEntity<?> get_offerte(@RequestBody @Valid UserRequest request){
        return userService.getListOfferteUser(request);
    }
    @PostMapping("/saveOfferta")
    public ResponseEntity<?> save_offerte(@RequestBody @Valid SaveOffertaRequest request){
        return userService.saveOffertaUtente(request);
    }
}
