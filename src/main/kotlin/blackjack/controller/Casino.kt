package blackjack.controller

import blackjack.domain.model.card.Deck
import blackjack.domain.model.card.HandCards.Companion.INIT_CARD_SIZE
import blackjack.domain.model.participant.CardStatus
import blackjack.domain.model.participant.Dealer
import blackjack.domain.model.participant.GameParticipant
import blackjack.domain.model.participant.Player
import blackjack.domain.model.progress.BetAmount
import blackjack.domain.model.progress.BetHistory
import blackjack.domain.model.progress.WinLossStatistics
import blackjack.view.InputView
import blackjack.view.OutputView

class Casino(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val deck: Deck,
) {
    fun blackJackGame() {
        val players: List<Player> = inputView.readPlayerNames().map { Player(it) }
        val betHistory = askBetAmountPhase(players)
        val dealer = Dealer()
        val participants: List<GameParticipant> = listOf(dealer) + players
        initDistributeCard(participants)
        outputView.showDistributeCardMessage(players)
        outputParticipantCardsInfo(dealer, players)
        runPlayersDrawPhase(players)
        runDealerDrawPhase(dealer)
        outputView.showCardsResult(participants)
        outputFinalResult(dealer, players)
    }

    private fun askBetAmountPhase(players: List<Player>): BetHistory {
        val betHistory = BetHistory()
        players.forEach { player ->
            val betAmount = askSingleBetAmount(player)
            betHistory.addBetLog(player, betAmount)
        }
        return betHistory
    }

    private fun askSingleBetAmount(player: Player): BetAmount =
        runCatching {
            BetAmount(inputView.readBetAmount(player.name))
        }.onFailure { exception ->
            exception.message?.let { outputView.showErrorMessage(it) }
        }.getOrNull() ?: askSingleBetAmount(player)

    private fun initDistributeCard(participants: List<GameParticipant>) {
        participants.forEach { participant ->
            while (participant.cardSize() < INIT_CARD_SIZE) {
                participant.handCards.addCard(deck.getCard())
            }
        }
    }

    private fun outputParticipantCardsInfo(
        dealer: Dealer,
        players: List<Player>,
    ) {
        outputView.showDealerFirstCardsInfo(dealer)
        outputView.showPlayersCardsInfo(players)
    }

    private fun runPlayersDrawPhase(players: List<Player>) {
        players.forEach { player ->
            while (player.handCards.getStatus() != CardStatus.BUST && isPlayerWantHit(player)) {
                player.handCards.addCard(deck.getCard())
                outputView.showPlayerCardsInfo(player)
            }
            if (player.isInitHandCard()) {
                outputView.showPlayerCardsInfo(player)
            }
        }
        outputView.endDrawPhase()
    }

    private fun isPlayerWantHit(player: Player): Boolean = inputView.readWantExtraCard(player.name)

    private fun runDealerDrawPhase(dealer: Dealer) {
        while (dealer.isDrawFinish()) {
            dealer.handCards.addCard(deck.getCard())
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
                player to winLossStatistics.calculatePlayerWinLoss(dealer, player)
            }
        outputView.showFinalResult(winLossStatistics, playersWinLoss)
    }
}
