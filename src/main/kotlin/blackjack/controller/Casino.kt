package blackjack.controller

import blackjack.domain.model.participant.CardStatus
import blackjack.domain.model.participant.Dealer
import blackjack.domain.model.participant.GameParticipant
import blackjack.domain.model.participant.Player
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
        val participants: List<GameParticipant> = listOf(dealer) + players
        initDistributeCard(participants)
        println()
        outputView.showDistributeCardMessage(players)
        outputParticipantCardsInfo(dealer, players)

        runPlayersDrawPhase(players)
        runDealerDrawPhase(dealer)
        outputView.showCardsResult(participants)
        outputFinalResult(dealer, players)
    }

    private fun initDistributeCard(participants: List<GameParticipant>) {
        participants.forEach { participant ->
            repeat(2) { participant.drawCard() }
        }
    }

    private fun outputParticipantCardsInfo(
        dealer: Dealer,
        players: List<Player>,
    ) {
        outputView.showDealerFirstCardsInfo(dealer)
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
        while (dealer.isDrawFinish()) {
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
                player to winLossStatistics.calculatePlayerWinLossByParticipant(dealer, player)
            }
        outputView.showFinalResult(winLossStatistics.getDealerWinLossText(), playersWinLoss)
    }
}
