package blackjack.controller

import blackjack.domain.model.card.Deck
import blackjack.domain.model.participant.Dealer
import blackjack.domain.model.participant.GameParticipant
import blackjack.domain.model.participant.Player
import blackjack.domain.model.participant.bet.BetAmount
import blackjack.domain.model.participant.bet.Profit
import blackjack.view.Views

class Casino(
    private val views: Views,
    private val deck: Deck,
) {
    fun blackJackGame() {
        val players: List<Player> = initPlayers()
        val dealer = Dealer()
        val participants: List<GameParticipant> = listOf(dealer) + players
        initDistributeCard(participants)
        views.output.showDistributeCardMessage(players)
        views.output.showInitCardInfo(participants)
        runPlayersDrawPhase(players)
        runDealerDrawPhase(dealer)
        views.output.showCardsResult(participants)
        outputFinalProfit(dealer, players)
    }

    private fun initPlayers(): List<Player> {
        val playerNames = views.input.readPlayerNames()
        return playerNames.map { playerName ->
            val betAmount = askSingleBetAmount(playerName)
            Player(playerName, betAmount)
        }
    }

    private fun askSingleBetAmount(playerName: String): BetAmount =
        runCatching {
            BetAmount(views.input.readBetAmount(playerName))
        }.onFailure { exception ->
            exception.message?.let { views.output.showErrorMessage(it) }
        }.getOrNull() ?: askSingleBetAmount(playerName)

    private fun initDistributeCard(participants: List<GameParticipant>) {
        participants.forEach { participant ->
            while (!participant.isInitHandCard()) {
                participant.drawCardFromDeck(deck)
            }
        }
    }

    private fun runPlayersDrawPhase(players: List<Player>) {
        players.forEach { player ->
            while (!player.isDrawFinish() && isPlayerWantHit(player)) {
                player.drawCardFromDeck(deck)
                views.output.showPlayerCardsInfo(player)
            }
            if (player.isInitHandCard()) {
                views.output.showPlayerCardsInfo(player)
            }
        }
        views.output.endDrawPhase()
    }

    private fun isPlayerWantHit(player: Player): Boolean = views.input.readWantExtraCard(player.name)

    private fun runDealerDrawPhase(dealer: Dealer) {
        while (dealer.isDrawFinish()) {
            dealer.drawCardFromDeck(deck)
            views.output.showDealerDrawMessage()
        }
    }

    private fun outputFinalProfit(
        dealer: Dealer,
        players: List<Player>,
    ) {
        val dealerProfitInfo: Pair<GameParticipant, Profit> = dealer to dealer.allPlayersMatchProfit(players)
        val playerProfitInfos: List<Pair<GameParticipant, Profit>> =
            players.map { player ->
                player to player.dealerMatchProfit(dealer)
            }
        views.output.showFinalProfit(listOf(dealerProfitInfo) + playerProfitInfos)
    }
}
