package blackjack.model

class Players(
    val value: List<Player>,
) {
    init {
        require(value.size in MIN_PLAYER_COUNT..MAX_PLAYER_COUNT) { "참여할 수 있는 플레이어는 ${MIN_PLAYER_COUNT}명 이상 ${MAX_PLAYER_COUNT}명까지 가능합니다." }
    }

    fun pickCard(
        cardDeck: CardDeck,
        times: Int,
    ) {
        value.forEach { player ->
            player.pickCard(cardDeck, times)
        }
    }

    fun getBlackjackPlayers(): List<Player> = value.filter { player -> player.isBlackjack() }

    fun getNotBlackjackPlayers(): List<Player> = value.filterNot { player -> player.isBlackjack() }

    fun getNotDyingPlayers(): List<Player> = value.filterNot { player -> player.isBust() }

    fun getDyingPlayers(): List<Player> = value.filter { player -> player.isBust() }

    companion object {
        private const val MIN_PLAYER_COUNT = 1
        private const val MAX_PLAYER_COUNT = 8
    }
}
