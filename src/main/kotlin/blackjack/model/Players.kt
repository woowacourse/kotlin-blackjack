package blackjack.model

class Players(
    val value: List<Player>,
) {
    fun findBlackjackPlayer(): List<Player> = value.filter { it.isBlackjack(true) }
}
