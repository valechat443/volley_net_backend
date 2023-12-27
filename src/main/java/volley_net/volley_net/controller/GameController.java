package volley_net.volley_net.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import volley_net.volley_net.payload.request.GameSpecificRequest;
import volley_net.volley_net.payload.request.WeekMaxRequest;
import volley_net.volley_net.service.GameService;

@RestController
@RequestMapping("game")
@RequiredArgsConstructor
@CrossOrigin
public class GameController {

    private final GameService gameService;

    @PostMapping("/getGame")
    public ResponseEntity<?> test_get_game(@RequestBody @Valid GameSpecificRequest request){
        return gameService.getGameById(request);
    }
    @PostMapping("/maxWeek")
    public ResponseEntity<?> max_week(@RequestBody @Valid WeekMaxRequest request){
        return gameService.get_week_max(request);
    }
}
