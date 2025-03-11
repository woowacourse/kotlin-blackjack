package blackjack.domain

import blackjack.enums.Result

class Participants(
    val dealer: Dealer,
    val players: List<Player>,
) {
    init {
        require(players.size in MIN_PLAYER_COUNT..MAX_PLAYER_COUNT) { ERROR_INVALID_PLAYER_COUNT }
    }

    fun drawCard(deck: Deck) {
        dealer.drawCard(deck.pick())
        players.forEach {
            it.drawCard(deck.pick())
        }
    }

    fun getDealerResult(): Map<Result, Int> = players.map { dealer.getResult(it.getScore()) }.groupingBy { it }.eachCount()

    fun getPlayerResults(): Map<String, Result> = players.associate { it.name to it.getResult(dealer.getScore()) }

    companion object {
        private const val MIN_PLAYER_COUNT = 1
        private const val MAX_PLAYER_COUNT = 7

        private const val ERROR_INVALID_PLAYER_COUNT = "1~7명의 플레이어가 참여할 수 있습니다."
    }
}
