package blackjack.model

class PlayerGroup {
    private var _players: List<Player> = emptyList()
    val players: List<Player>
        get() = _players

    fun addPlayer(playerNames: List<String>) {
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
}
