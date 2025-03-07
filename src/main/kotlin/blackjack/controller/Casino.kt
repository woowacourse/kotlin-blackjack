package blackjack.controller

import blackjack.domain.model.Dealer
import blackjack.domain.model.Player
import blackjack.domain.model.Rule
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

        runPlayersDrawPhase(players)
        runDealerDrawPhase(dealer)
        outputView.showCardsResult(listOf(dealer) + players)
    }

    private fun outputParticipantCardsInfo(
        dealer: Dealer,
        players: List<Player>,
    ) {
        outputView.showDealerCardsInfo(dealer)
        players.forEach { outputView.showPlayerCardsInfo(it) }
    }

    private fun runPlayersDrawPhase(players: List<Player>) {
        players.forEach { player ->
            while (!Rule.isBurst(player.showCards())) {
                val response: Boolean = inputView.readWantExtraCard(player.name)

                if (!response) {
                    if (player.showCards().size == 2) {
                        outputView.showPlayerCardsInfo(player)
                        // todo(중복 if문 로직 제거 예정)
                    }
                    break
                }
                player.drawCard()
                outputView.showPlayerCardsInfo(player)
            }
        }
    }

    private fun runDealerDrawPhase(dealer: Dealer) {
        while (Rule.calculateShouldDrawByCards(dealer.showCards())) {
            dealer.drawCard()
            outputView.showDealerDrawMessage()
        }
    }
}
