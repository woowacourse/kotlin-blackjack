package domain.gamer

import domain.gamer.state.PlayerState

data class Player(
    val name: String,
    val state: PlayerState
)
