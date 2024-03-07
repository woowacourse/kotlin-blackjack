package blackjack.model

class PlayerGroup {
    private var _players: List<Player> = emptyList()
    val players: List<Player>
        get() = _players

    fun addPlayer(playerNames: List<String>) {
        require(_players.size + playerNames.size in PLAYERS_COUNT_RANGE) { "플레이어의 수는 1 ~ 8명 사이여야 합니다" }
        _players = playerNames.map { Player(HumanName(it)) }
    }

    fun drawPlayerCard(
        gameDeck: GameDeck,
        hitOrStay: (humanName: HumanName) -> Boolean,
        showPlayerCards: (player: Player) -> Unit,
    ) {
        players.forEach { player ->
            while (player.hand.state == UserState.RUNNING) {
                if (hitOrStay(player.humanName)) {
                    player.takeCard(gameDeck.drawCard())
                    showPlayerCards(player)
                } else {
                    player.hand.changeState(UserState.STAY)
                }
            }
        }
    }

    companion object {
        val PLAYERS_COUNT_RANGE = 1..8
    }
}
