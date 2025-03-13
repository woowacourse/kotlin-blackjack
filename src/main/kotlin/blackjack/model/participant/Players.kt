package blackjack.model.participant

class Players(playerNames: List<String>) {
    private val _value: MutableList<Player> = playerNames.map { Player(it) }.toMutableList()
    val value: List<Player>
        get() = _value.toList()

    init {
        require(playerNames.size == playerNames.toSet().size) { ERROR_INVALID_PLAYER_NAMES }
    }

    companion object {
        private const val ERROR_INVALID_PLAYER_NAMES = "플레이어 이름은 중복될 수 없습니다."
    }
}
