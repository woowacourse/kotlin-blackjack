package blackjack.model

class PlayerGroup {
    private var _players: List<Player> = emptyList()
    val players: List<Player>
        get() = _players

    fun addPlayer(playerNames: List<String>) {
        require(_players.size + playerNames.size in PLAYERS_COUNT_RANGE) { "플레이어의 수는 1 ~ 8명 사이여야 합니다" }
        _players = playerNames.map { Player(Name(it)) }
    }

    fun drawPlayerCard(
        gameDeck: GameDeck,
        hitOrStay: (name: Name) -> Boolean,
        returnPlayerInfo: (player: Player) -> Unit,
    ) {
        players.forEach { player ->
            player.chooseHitOrStay(gameDeck, hitOrStay, returnPlayerInfo)
        }
    }

    companion object {
        val PLAYERS_COUNT_RANGE = 1..8
    }
}
