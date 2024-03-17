package blackjack.model

import blackjack.model.GameDeck.Companion.CARD_DRAW_DEFAULT_INDEX

class PlayerGroup {
    private var _players: List<Player> = emptyList()
    val players: List<Player>
        get() = _players

    fun addPlayer(playerNames: List<String>) {
        require(_players.size + playerNames.size in PLAYERS_COUNT_RANGE) { INVALID_PLAYERS_COUNT_ERROR_MESSAGE }
        _players += playerNames.map { Player(userInfo = UserInfo(nickname = Nickname(it))) }
    }

    fun startBetting(amount: (player: Player) -> Int) {
        players.forEach { player ->
            val betAmount = BetAmount(amount(player))
            player.userInfo.plusBet(betAmount)
        }
    }

    fun drawPlayerCard(
        gameDeck: GameDeck,
        shouldDrawCard: (player: Player) -> Boolean,
        showPlayerCards: (cardHolder: CardHolder) -> Unit,
    ) {
        players.forEach { player ->
            player.drawCard(
                card = { gameDeck.drawCard(CARD_DRAW_DEFAULT_INDEX) },
                shouldDrawCard = { shouldDrawCard(player) },
                showPlayerCards = showPlayerCards,
            )
        }
    }

    companion object {
        val PLAYERS_COUNT_RANGE = 1..8

        const val INVALID_PLAYERS_COUNT_ERROR_MESSAGE = "플레이어의 수는 1 ~ 8명 사이여야 합니다"
    }
}
