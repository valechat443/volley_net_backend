package volley_net.volley_net.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import volley_net.volley_net.entity.*;
import volley_net.volley_net.payload.request.*;
import volley_net.volley_net.payload.response.GetRankResponse;
import volley_net.volley_net.payload.response.GetUserResponse;
import volley_net.volley_net.payload.response.NewUserLoginResponse;
import volley_net.volley_net.payload.response.UpdateMoneyResponse;
import volley_net.volley_net.repository.TeamRepository;
import volley_net.volley_net.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final TokenService tokenService;

    /**
     *
     * metodo per la conversione di una SignupRequest in un entità User
     */
    private User fromRequestToEntity(SignupRequest request) {
        User u = new User();
        String encryptedPassword = new DigestUtils("SHA3-256").digestAsHex(request.getPassword()); //algoritmo per la conversione della psw
        u = new User(request.getMail(), request.getUsername(), encryptedPassword, request.isAdmin()); //generazione di un User
        return u;
    }
    /**
     * metodo per salvare il nuovo utente creato da fromRequestToEntity sul db, metodo ereditato da JPA
     * @param request che passa
     */
    public ResponseEntity<?> save (SignupRequest request){
        User newuser=fromRequestToEntity(request);
        User olduser=userRepository.findByUsername(request.getUsername());
        if(olduser == null || !newuser.getUsername().equals(olduser.getUsername())  ) { //se il nome utente non esiste già creo l'utente
            userRepository.save(newuser);
            NewUserLoginResponse response = new NewUserLoginResponse(tokenService.createToken(newuser.getId_user()));
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("utente già esistente",HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<?> getUser(UserRequest request){
        try{
            UserToken id_utente = tokenService.getUserIdFromToken(request.getToken());
            User u = userRepository.getUserById(id_utente.getId_token());
            if(id_utente!=null && u!=null) {
                GetUserResponse response = new GetUserResponse(u.getId_user(),u.getMail(),u.getUsername(),u.isVerified(),u.isAdmin(),u.getMoney(),u.getCount_bet());
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Id_user errato", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>("Errore nel Server", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> login(UserRequest request){
        return null;
    }


    /**
     * metodo per aggiungere o togliere un determinato num di money
     * @param request che passa
     */
    public ResponseEntity<?> update_money(UpdateMoneyRequest request){
        try{
            UserToken id_utente = tokenService.getUserIdFromToken(request.getToken());
            if(id_utente!=null && userRepository.getUserById(id_utente.getId_token())!=null){
                userRepository.updateMoney(request.getNum(),id_utente.getId_token());
                UpdateMoneyResponse response = new UpdateMoneyResponse(userRepository.getMoneyById(id_utente.getId_token()));
                return new ResponseEntity<>(response,HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Id_user errato", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>("Errore nel Server", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> new_team_list(UserRequest request){
        return null;
    }

    public ResponseEntity<?> get_rank(GetRankRequest request){
        try{
            UserToken id_utente = tokenService.getUserIdFromToken(request.getToken());
            User u = userRepository.getUserById(id_utente.getId_token());
            if(id_utente!=null && u!=null) {
                GetRankResponse response = new GetRankResponse(u.getId_user(), u.getUsername(), u.getCount_bet());
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Id_user errato", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>("Errore nel Server", HttpStatus.BAD_REQUEST);
        }
    }
}
