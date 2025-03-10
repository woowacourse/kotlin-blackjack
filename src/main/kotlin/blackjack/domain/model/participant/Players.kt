package blackjack.domain.model.participant

import blackjack.domain.model.card.Deck

class Players(
    private val players: List<Player>,
) {
    init {
        require(players.size <= 7) { ERROR_OUT_OF_SIZE }
    }

    fun initialDraw(deck: Deck) {
        repeat(2) {
            draw(deck)
        }
    }

    fun draw(deck: Deck) {
        players.forEach {
            it.drawCard(deck)
        }
    }

    fun showCards(outputCardInfo: (Player) -> Unit) {
        players.forEach { player ->
            outputCardInfo(player)
        }
    }

    companion object {
        private const val ERROR_OUT_OF_SIZE = "플레이어는 최대 7명까지만 가능합니다."
    }
}
