package blackjack.domain.gameResult.state

import blackjack.domain.participant.Player

interface PlayerState : State {
    val player: Player

    companion object {
        fun of(player: Player): PlayerState {
            if (player.isBust()) return PlayerBust(player)
            return if (player.isBlackJack()) {
                PlayerBlackJack(player)
            } else {
                PlayerStay(player)
            }
        }
    }
}
