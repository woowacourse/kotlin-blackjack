package blackjack.controller

import blackjack.domain.model.GameParticipant
import blackjack.domain.model.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class Casino(
    val inputView: InputView,
    val outputView: OutputView,
) {
    fun gameStart() {
        val players: List<Player> = inputView.readPlayerNames().map { Player(it) }
        println()
        outputView.showDistributeCardMessage(players)
        outputParticipantCardsInfo(players)
    }

    private fun outputParticipantCardsInfo(gameParticipants: List<GameParticipant>) {
        gameParticipants.forEach { outputView.showCardsInfo(it) }
    }
}
