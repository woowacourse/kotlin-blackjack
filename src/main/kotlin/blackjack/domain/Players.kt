package blackjack.domain

import blackjack.enums.Result

class Players(
    val players: List<Player>,
) {
    init {
        require(players.size in MIN_PLAYER_COUNT..MAX_PLAYER_COUNT) { ERROR_INVALID_PLAYER_COUNT }
    }

    fun dealCards() {
        players.forEach { it.drawCard(Deck.pick()) }
    }

    fun calculateResult(dealer: Dealer): Map<Player, Result> = players.associateWith { it.getResult(dealer) }

    companion object {
        private const val MIN_PLAYER_COUNT = 1
        private const val MAX_PLAYER_COUNT = 7

        private const val ERROR_INVALID_PLAYER_COUNT = "1~7명의 플레이어가 참여할 수 있습니다."
    }
}
