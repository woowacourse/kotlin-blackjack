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

        initializeGame(dealer, players)
        playGame(players, dealer)
        concludeGame(dealer, players)
    }

    private fun initializeGame(
        dealer: Dealer,
        players: List<Player>,
    ) {
        initCardDistribute(listOf(dealer) + players)
        views.output.showDistributeCardMessage(players)
        views.output.showInitCardInfo(listOf(dealer) + players)
    }

    private fun playGame(
        players: List<Player>,
        dealer: Dealer,
    ) {
        playersDrawPhase(players)
        dealerDrawPhase(dealer)
    }

    private fun concludeGame(
        dealer: Dealer,
        players: List<Player>,
    ) {
        views.output.showCardsResult(listOf(dealer) + players)
        showFinalProfit(dealer, players)
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

    private fun initCardDistribute(participants: List<GameParticipant>) {
        participants.forEach { participant ->
            while (!participant.isInitHandCard) {
                participant.fromDeckCardDraw(deck)
            }
        }
    }

    private fun playersDrawPhase(players: List<Player>) {
        players.forEach { player ->
            while (!player.isDrawFinish && isPlayerWantHit(player)) {
                player.fromDeckCardDraw(deck)
                views.output.showPlayerCardsInfo(player)
            }
            if (player.isInitHandCard) {
                views.output.showPlayerCardsInfo(player)
            }
        }
        views.output.endDrawPhase()
    }

    private fun isPlayerWantHit(player: Player): Boolean = views.input.readWantExtraCard(player.name)

    private fun dealerDrawPhase(dealer: Dealer) {
        while (dealer.isDrawFinish) {
            dealer.fromDeckCardDraw(deck)
            views.output.showDealerDrawMessage()
        }
    }

    private fun showFinalProfit(
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
