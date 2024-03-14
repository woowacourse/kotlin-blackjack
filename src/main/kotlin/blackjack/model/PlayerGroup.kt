package blackjack.model

class PlayerGroup {
    private var _players: List<Player> = emptyList()
    val players: List<Player>
        get() = _players

    fun addPlayer(playerNames: List<String>) {
        require(_players.size + playerNames.size in PLAYERS_COUNT_RANGE) { INVALID_PLAYERS_COUNT_ERROR_MESSAGE }
        _players += playerNames.map { Player(userInfo = UserInfo(nickname = Nickname(it))) }
    }

    fun startBetting(amount: (player: Player) -> String) {
        players.forEach { player ->
            val betAmount = BetAmount.from(amount(player))
            player.userInfo.betting(betAmount)
        }
    }

    fun drawPlayerCard(
        shouldDrawCard: (player: Player) -> Boolean,
        newPlayer: (player: Player) -> Unit,
    ) {
        players.forEach { player ->
            player.drawCard(
                shouldDrawCard = { shouldDrawCard(player) },
                newCardHolder = { newPlayer(it as Player) },
            )
        }
    }

    companion object {
        val PLAYERS_COUNT_RANGE = 1..8

        const val INVALID_PLAYERS_COUNT_ERROR_MESSAGE = "플레이어의 수는 1 ~ 8명 사이여야 합니다"
    }
}
