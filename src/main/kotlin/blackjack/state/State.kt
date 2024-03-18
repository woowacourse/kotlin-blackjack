package blackjack.state

import blackjack.model.participant.Player

sealed class State {
    abstract fun decisionState(): State

    class Start(private val player: Player) : State() {
        override fun decisionState(): State {
            player.initializeCards()
            return if (player.isBlackjack()) Finished.Blackjack else Running(player)
        }
    }

    class Hit(private val player: Player) : State() {
        override fun decisionState(): State {
            player.addCard()
            return if (player.isBust()) Finished.Bust else Running(player)
        }
    }

    class Running(private val player: Player) : State() {
        override fun decisionState(): State = this
    }

    sealed class Finished : State() {
        override fun decisionState(): State = this

        data object Blackjack : Finished()

        data object Bust : Finished()

        data object Stay : Finished()
    }
}
