package volley_net.volley_net.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/**
 * lista di punti dei tempi di una partita
 */
public class PeriodsTeamsGameSpecific {

  /**
   * lista di punteggi, un tempo= un punteggio
   */
  private List<Integer> points;
}
