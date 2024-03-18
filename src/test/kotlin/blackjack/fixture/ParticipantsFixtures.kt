package blackjack.fixture

import blackjack.model.Betting
import blackjack.model.participant.Player
import blackjack.state.State

fun createPlayer(
    betting: Int = 10_000,
    state: State,
    name: String = "송둥",
): Player = Player(name, Betting(betting), state) { true }
