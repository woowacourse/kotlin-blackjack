package blackjack.domain

import blackjack.domain.dealer.Dealer
import blackjack.domain.dealer.DrawResult
import blackjack.domain.gameResult.TotalGameResult
import blackjack.domain.player.Player

// TODO: 스스로 장/단점을 잘 판단하자.
class BlackJackGame(
    val dealer: Dealer = Dealer(),
    private val blackJackReferee: BlackJackReferee = BlackJackReferee(),
) {

    lateinit var players: List<Player>
        private set

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
        checkCurrentCards: (player: Player) -> Unit = { },
    ) {
        players.forEach { player ->
            drawCardsRepeatedly(
                player = player,
                isPlayerWantedAdditionalCards = { isPlayerWantedAdditionalCards(player) },
                checkCurrentCards = { checkCurrentCards(player) }
            )
        }
    }

    private fun drawCardsRepeatedly(
        player: Player,
        isPlayerWantedAdditionalCards: (player: Player) -> Boolean,
        checkCurrentCards: (player: Player) -> Unit = { },
    ) {
        do {
            val isPlayerWanted = isPlayerWantedAdditionalCards(player)
        } while (isPlayerWanted && player.drawCard { checkCurrentCards(player) })

        player.checkIsDrawnNothing { checkCurrentCards(player) }
    }

    fun drawAdditionalCardsForDealer(): DrawResult {
        val isDealerDrawn = dealer.drawCard()

        return isDealerDrawn
    }

    fun judgeGameResults(): TotalGameResult = blackJackReferee.judgeTotalGameResults(players, dealer)
}
