package blackjack.domain.player

import blackjack.domain.card.CardsState
import blackjack.domain.gameResult.PlayerGameResult
import blackjack.domain.gameResult.PlayerGameResults

class BlackJackPlayers(players: List<Player>) {

    private val _players = players.toList()
    val players: List<Player>
        get() = _players.toList()

    fun drawAdditionalCardsForPlayers(
        isPlayerWantedAdditionalCards: (player: Player) -> Boolean,
        checkCurrentCards: (player: Player) -> Unit = { },
    ) {
        players.forEach { player ->
            player.drawCardsRepeatedly(
                isPlayerWantedAdditionalCards = { isPlayerWantedAdditionalCards(player) },
                checkCurrentCards = { checkCurrentCards(player) }
            )
        }
    }

    fun judgePlayerGameResults(otherCardsState: CardsState): PlayerGameResults {
        val playerGameResults = players.map { player ->
            PlayerGameResult.of(player, otherCardsState)
        }

        return PlayerGameResults(playerGameResults)
    }
}
