package blackjack.model

class Participants {
    private var _players: List<Player> = emptyList()
    val players: List<Player>
        get() = _players

    fun addPlayer(playerNames: List<String>) {
        _players = playerNames.map { Player(it) }
    }
}
