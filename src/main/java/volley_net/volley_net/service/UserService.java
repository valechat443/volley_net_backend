package volley_net.volley_net.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import volley_net.volley_net.entity.User;
import volley_net.volley_net.payload.request.SignupRequest;
import volley_net.volley_net.payload.request.UpdateMoneyRequest;
import volley_net.volley_net.payload.request.UserRequest;
import volley_net.volley_net.payload.response.NewUserLoginResponse;
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
        UserToken id_utente=tokenService.getUserIdFromToken(request.getToken());
        String encryptedPassword = new DigestUtils("SHA3-256").digestAsHex("pippo"); //algoritmo per la conversione della psw

        User u=new User("bob.bobby@gmail.com","bob",encryptedPassword,false);
        return new ResponseEntity<>(u,HttpStatus.OK);
    }

    public ResponseEntity<?> login(UserRequest request){
        return null;
    }


    public ResponseEntity<?> update_money(UpdateMoneyRequest request){
        UserToken id_utente = tokenService.getUserIdFromToken(request.getToken());
        userRepository.updateMoney(request.getNum(),id_utente.getId_token());
        return new ResponseEntity<>("Fine operazione",HttpStatus.OK);
    }
    public ResponseEntity<?> new_team_list(UserRequest request){
        return null;
    }
    public ResponseEntity<?> get_money(UserRequest request){
        return null;
    }
    public ResponseEntity<?> get_rank(UserRequest request){
        return null;
    }
}
