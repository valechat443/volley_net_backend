package volley_net.volley_net.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import volley_net.volley_net.payload.request.SignupRequest;
import volley_net.volley_net.payload.request.UpdateMoneyRequest;
import volley_net.volley_net.payload.request.UserRequest;
import volley_net.volley_net.service.UserService;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    private final UserService userService;

    @PostMapping("/signup") //chiamata al servizio di registrazione
    public ResponseEntity<?> save(@RequestBody @Valid SignupRequest request) { return userService.save(request);}

    @PostMapping("/chiedi")
    public ResponseEntity<?> getUser(@RequestBody @Valid UserRequest request) { return userService.getUser(request);}

    @PostMapping("/updateMoney")
    public ResponseEntity<?> update_money(@RequestBody @Valid UpdateMoneyRequest request){
        return userService.update_money(request);
    }
}
