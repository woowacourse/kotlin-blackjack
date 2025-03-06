package blackjack.controller

import blackjack.domain.model.Dealer
import blackjack.domain.model.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class Casino(
    val inputView: InputView,
    val outputView: OutputView,
) {
    fun gameStart() {
        val players: List<Player> = inputView.readPlayerNames().map { Player(it) }
        val dealer: Dealer = Dealer()
        println()
        outputView.showDistributeCardMessage(players)
        outputParticipantCardsInfo(dealer, players)
    }

    private fun outputParticipantCardsInfo(
        dealer: Dealer,
        players: List<Player>,
    ) {
        outputView.showDealerCardsInfo(dealer)
        players.forEach { outputView.showPlayerCardsInfo(it) }
    }
}
