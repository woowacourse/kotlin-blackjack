package blackjack.model

import blackjack.exception.ErrorCode.INVALID_PLAYERS_COUNT_ERROR
import blackjack.exception.ExceptionsHandler.handleValidation

class PlayerGroup {
    private var _players: List<Player> = emptyList()
    val players: List<Player>
        get() = _players

    fun addPlayer(playerNames: List<String>) {
        handleValidation<String>(INVALID_PLAYERS_COUNT_ERROR) { _players.size + playerNames.size in PLAYERS_COUNT_RANGE }
        _players += playerNames.map { Player(nickname = Nickname(it)) }
    }

    fun drawPlayerCard(
        gameDeck: GameDeck,
        hitOrStay: (nickname: Nickname) -> Boolean,
        showPlayerCards: (player: Player) -> Unit,
    ): GameDeck {
        players.forEach { player ->
            player.drawCardsForPlayer(gameDeck, hitOrStay, showPlayerCards)
        }
        return gameDeck
    }

    companion object {
        val PLAYERS_COUNT_RANGE = 1..8
    }
}
