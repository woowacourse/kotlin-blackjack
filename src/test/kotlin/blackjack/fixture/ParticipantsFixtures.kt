package blackjack.fixture

import blackjack.model.participant.Player
import blackjack.state.State

fun createPlayer(
    name: String = "송둥",
    state: State,
) = Player(name, state) { true }
