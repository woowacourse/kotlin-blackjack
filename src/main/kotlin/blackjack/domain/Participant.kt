package blackjack.domain

import blackjack.domain.state.PlayingState
import blackjack.domain.state.Ready

abstract class Participant(val name: String) {
    var state: PlayingState = Ready()
}
