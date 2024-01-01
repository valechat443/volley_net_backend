package volley_net.volley_net.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import volley_net.volley_net.payload.request.BetFutureRequest;
import volley_net.volley_net.payload.request.CreateBetRequest;
import volley_net.volley_net.service.BetService;


@RestController
@RequestMapping("bet")
@RequiredArgsConstructor
@CrossOrigin
public class BetController {
    private  final BetService betService;

    @PostMapping("/save")
    public ResponseEntity<?> create_bet(@RequestBody @Valid CreateBetRequest request){
        return betService.salva_bet(request);
    }

    @PostMapping("/betList")
    public  ResponseEntity<?> get_future_bet(@RequestBody @Valid BetFutureRequest request){
        return betService.get_future_bets(request);
    }
}
