package blackjack.model

class Participants(
    val players: List<Player>,
    val dealer: Dealer,
) {
    init {
        require(
            players.size in MIN_PLAYER_COUNT..MAX_PLAYER_COUNT,
        ) { "참여할 수 있는 플레이어는 ${MIN_PLAYER_COUNT}명 이상 ${MAX_PLAYER_COUNT}명까지 가능합니다." }
    }

    fun pickCard(
        cardDeck: CardDeck,
        times: Int,
    ) {
        players.forEach { player ->
            player.pickCard(cardDeck, times)
        }
        dealer.pickCard(cardDeck, times)
    }

    companion object {
        private const val MIN_PLAYER_COUNT = 1
        private const val MAX_PLAYER_COUNT = 8
    }
}
