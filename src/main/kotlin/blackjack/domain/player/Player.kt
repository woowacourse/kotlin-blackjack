package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.result.GameResult

abstract class Player(
    val name: String,
    val cards: Cards = Cards()
) {

    abstract fun canHit(): Boolean

    fun addCard(card: Card) {
        cards.addCard(card)
    }

    fun getGameResult(otherPlayerCardsSum: Int): GameResult {
        val cardsSum: Int = cards.sum()

        return when {
            cardsSum > MAX_SUM_NUMBER -> GameResult.LOSE
            otherPlayerCardsSum > MAX_SUM_NUMBER -> GameResult.WIN
            otherPlayerCardsSum > cardsSum -> GameResult.LOSE
            otherPlayerCardsSum == cardsSum -> GameResult.DRAW
            else -> GameResult.WIN
        }
    }

    fun setInitialCards(cards: Cards) =
        cards.values.forEach { addCard(it) }

    companion object {
        const val CARD_SETTING_COUNT = 2
        const val MAX_SUM_NUMBER = 21
    }
}
