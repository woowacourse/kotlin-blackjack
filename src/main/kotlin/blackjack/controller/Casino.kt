package blackjack.controller

import blackjack.domain.model.card.Deck
import blackjack.domain.model.participant.Dealer
import blackjack.domain.model.participant.GameParticipant
import blackjack.domain.model.participant.GameParticipants
import blackjack.domain.model.participant.Player
import blackjack.domain.model.participant.bet.BetAmount
import blackjack.view.Views

class Casino(
    private val views: Views,
    private val deck: Deck,
) {
    fun blackJackGame() {
        val gameParticipants = initGameParticipants()
        initializeGame(gameParticipants)
        playGame(gameParticipants)
        concludeGame(gameParticipants)
    }

    private fun initGameParticipants(): GameParticipants {
        val players: List<Player> = initPlayers()
        val dealer = Dealer()
        return GameParticipants(dealer, players)
    }

    private fun initializeGame(gameParticipants: GameParticipants) {
        initCardDistribute(gameParticipants.gameParticipants)
        views.output.showDistributeCardMessage(gameParticipants.players)
        views.output.showInitCardInfo(gameParticipants.gameParticipants)
    }

    private fun playGame(gameParticipants: GameParticipants) {
        playersDrawPhase(gameParticipants.players)
        dealerDrawPhase(gameParticipants.dealer)
    }

    private fun concludeGame(gameParticipants: GameParticipants) {
        views.output.showCardsResult(gameParticipants.gameParticipants)
        views.output.showParticipantsFinalProfit(gameParticipants.profitInfos)
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
}
