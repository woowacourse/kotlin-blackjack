package blackjack.fixture

import blackjack.model.Betting
import blackjack.model.participant.Dealer
import blackjack.model.participant.Player
import blackjack.state.State

fun createPlayer(
    betting: Betting = Betting(10_000),
    state: State,
    name: String = "송둥",
) = Player(name, betting, state) { true }

fun createDealer(
    betting: Betting = Betting(0),
    state: State,
) = Dealer(betting, state)
