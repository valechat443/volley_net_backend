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
import volley_net.volley_net.payload.response.*;
import volley_net.volley_net.repository.*;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    /**
     * operazioni del database di user {@link UserRepository}
     */
    private final UserRepository userRepository;
    /**
     * operazioni del database di team {@link TeamRepository}
     */
    private final TeamRepository teamRepository;
    /**
     * operazioni del database di group {@link GroupRepository}
     */
    private final GroupRepository groupRepository;
    /**
     * {@link TokenService} token identificativo di user
     */
    private final TokenService tokenService;
    /**
     * operazioni del database di team_list {@link TeamListRepository}
     */
    private final TeamListRepository teamListRepository;
    /**
     * operazioni del database di offerta_utente {@link Offerte_utente}
     */
    private final OffertaUtenteRepository offertaUtenteRepository;

    /**
     * metodo per la conversione di una SignupRequest in un entità User
     * @param request {@link SignupRequest}
     * @return un oggetto {@link User}
     */
    private User fromRequestToEntity(SignupRequest request) {
        User u = new User();
        String encryptedPassword = new DigestUtils("SHA3-256").digestAsHex(request.getPassword()); //algoritmo per la conversione della psw
        u = new User(request.getMail(), request.getUsername(), encryptedPassword, request.isAdmin()); //generazione di un User
        return u;
    }
    /**
     * metodo per salvare il nuovo utente creato da fromRequestToEntity sul db, metodo ereditato da JPA
     * @param request {@link SignupRequest}
     * @return una {@link NewUserLoginResponse} con l'utente salvato
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

    /**
     * metodo per avere un user partendo da un token
     * @param request {@link UserRequest}
     * @return {@link GetUserResponse} con l'user richiesto
     */
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

    /**
     * metodo per effettuare il login nell'applicativo
     * @param request {@link LoginRequest}
     * @return {@link LoginResponse} con l'esito del login
     */
    public ResponseEntity<?> login(LoginRequest request){
        try{
            User u = userRepository.getUserByMail(request.getMail());
            String encryptedPassword = new DigestUtils("SHA3-256").digestAsHex(request.getPassword()); //algoritmo per la conversione della psw
            String password = u.getPassword();
            if(u!=null && encryptedPassword.equals(u.getPassword())) {
                LoginResponse response = new LoginResponse(true,tokenService.createToken(u.getId_user()));
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                LoginResponse response = new LoginResponse(false,"");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>("Errore nel Server", HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * metodo per aggiungere o togliere un determinato num di money d un determinato user
     * @param request  {@link UpdateMoneyRequest}
     * @return {@link UpdateMoneyResponse} contenente il nuovo bilancio dell'utente
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

    /**
     * metodo per salvare una nuova associazione tra un user e un team su cui ha scommesso
     * @param request {@link NewTeamListRequest}
     * @return  {@link NewTeamListResponse} con l'esito dell'operazione di salvataggio
     */
    public ResponseEntity<?> new_team_list(NewTeamListRequest request){
        try{
            UserToken id_utente = tokenService.getUserIdFromToken(request.getToken());

            //creo team_list
            User u = userRepository.getUserById(id_utente.getId_token());
            Team_season ts = teamRepository.GetTeamSeasonByIdTeamIdSeason(request.getId_team(), request.getSeason());
            Group g = groupRepository.GetGroupByIdGroup(groupRepository.GetIdGroupByIdTeamSeason(ts.getId_team_season()).getId_group().getId_group());

            Team_list tl = new Team_list(u,ts,g);
            NewTeamListResponse response = new NewTeamListResponse(false);
            if(tl!= null){
                teamListRepository.save(tl);
                response.setConferma(true);
            }



            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("Errore nel Salvataggio", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * metodo per avere la lista degli utenti ordinati per quante scommesse hanno vinto
     * @return lista di {@link ListUserRankResponse} contenente gli user in ordine di count_bet
     */
    public ResponseEntity<?> get_rank(){
        try{
            List<User> us = userRepository.getUserRank();
            List<ListUserRankResponse> response = new ArrayList<>();
            for (User user : us) {
                if(!user.isAdmin()){
                    response.add(new ListUserRankResponse(user.getId_user(),user.getUsername(),user.getCount_bet()));
                }
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Errore nel Server", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * metodo per avere le offerte acquistate da un determinato user
     * @param request {@link UserRequest}
     * @return Lista di Offerte_utente {@link List<Offerte_utente>}
     */
    public ResponseEntity<?> getListOfferteUser(UserRequest request){
        try{
        UserToken id_utente = tokenService.getUserIdFromToken(request.getToken());

        List<Offerte_utente> elenco=offertaUtenteRepository.getListaOfferte(id_utente.getId_token());

        List<String> nomi= new ArrayList<>();
        for(Offerte_utente of:elenco){
            nomi.add(of.getName_offerta());
        }

        if(!nomi.isEmpty()) {
            return new ResponseEntity<>(nomi, HttpStatus.OK);
        }
            return new ResponseEntity<>("nessuna offerta trovata", HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>("Errore nel Server", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * metodo per salvare una nuova offerta acquistata da un utente
     * @param request {@link  SaveOffertaRequest}
     * @return ResponseEntity con l'esito del'operazione di salvataggio
     */
    public ResponseEntity<?> saveOffertaUtente(SaveOffertaRequest request){
        try{
            UserToken id_utente = tokenService.getUserIdFromToken(request.getToken());
            User u = userRepository.getUserById(id_utente.getId_token());
            Offerte_utente of = new Offerte_utente(u,request.getNome_offerta());
            Offerte_utente ris= offertaUtenteRepository.save(of);
            if(ris!=null){
                return new ResponseEntity<>("ok", HttpStatus.CREATED);
            }
            return new ResponseEntity<>("Errore nel salvataggio", HttpStatus.NOT_MODIFIED);
        }catch (Exception e){
            return new ResponseEntity<>("Errore nel Server", HttpStatus.BAD_REQUEST);
        }

    }
}
