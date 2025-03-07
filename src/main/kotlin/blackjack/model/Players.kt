package blackjack.model

class Players(
    val value: List<Player>,
) {
    init {
        require(value.size == value.toSet().size) { "플레이어 이름은 중복될 수 없습니다." }
    }

    fun findBlackjackPlayer(): List<Player> = value.filter { it.isBlackjack(true) }
}
