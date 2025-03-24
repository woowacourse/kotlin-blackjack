package blackjack.domain

class Players(
    private val _players: List<Player>,
) {
    init {
        require(_players.size in 1..8) { "참가자는 최소 1명에서 최대 8명까지만 가능합니다." }
    }

    val players: List<Player>
        get() = _players

    fun from(names: List<String>): Players {
        val players =
            names.map { name ->
                Player(name, bettingAmount)
            }
        return Players(players)
    }
}
