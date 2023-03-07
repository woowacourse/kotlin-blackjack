package blackjack.domain

import blackjack.domain.dealer.Dealer
import blackjack.domain.dealer.DrawResult
import blackjack.domain.gameResult.TotalGameResult
import blackjack.domain.player.Player

class BlackJackGame(
    val dealer: Dealer = Dealer(),
    private val blackJackReferee: BlackJackReferee = BlackJackReferee(),
) {

    lateinit var players: List<Player>
        private set

    // TODO: mapIndex 맘에 안듦...
    fun initPlayers(
        playerNames: List<String>,
        battingMoneys: List<Int>,
    ) {
        players = playerNames.mapIndexed { playerIndex, playerName ->
            Player(playerName, battingMoneys[playerIndex])
        }
    }

    fun drawAdditionalCardsForPlayers(
        isPlayerWantedAdditionalCards: (player: Player) -> Boolean,
    ) {
        players.forEach { player ->
            drawCardsRepeatedly(player) { isPlayerWantedAdditionalCards(player) }
        }
    }

    private fun drawCardsRepeatedly(
        player: Player,
        isPlayerWantedAdditionalCards: (player: Player) -> Boolean,
    ) {
        do {
            val isPlayerWanted = isPlayerWantedAdditionalCards(player)
        } while (isPlayerWanted && player.drawCard())

        player.checkIsDrawnNothing()
    }

    fun drawAdditionalCardsForDealer(): DrawResult {
        val isDealerDrawn = dealer.drawCard()

        return isDealerDrawn
    }

    fun judgeGameResults(): TotalGameResult {
        val totalGameResult = blackJackReferee.judgeTotalGameResults(players, dealer)

        return totalGameResult
    }
}
