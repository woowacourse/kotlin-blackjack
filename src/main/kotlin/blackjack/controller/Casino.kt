package blackjack.controller

import blackjack.domain.model.participant.CardStatus
import blackjack.domain.model.participant.Dealer
import blackjack.domain.model.participant.Player
import blackjack.domain.model.progress.Rule
import blackjack.domain.model.progress.WinLossStatistics
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
        outputFinalResult(dealer, players)
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
            while (player.handCards.getStatus() != CardStatus.BUST) {
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

    private fun outputFinalResult(
        dealer: Dealer,
        players: List<Player>,
    ) {
        val winLossStatistics = WinLossStatistics()
        val playersWinLoss =
            players.map { player ->
                player to winLossStatistics.calculatePlayerWinLoss(dealer.showCards(), player.showCards())
            }
        outputView.showFinalResult(winLossStatistics.getDealerWinLossText(), playersWinLoss)
    }
}
