package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

abstract class Player(
    val name: String,
    val cards: Cards = Cards()
) {

    abstract fun canHit(): Boolean

    abstract fun decideGameResult(otherPlayer: Player)

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun setInitialCards(cards: Cards) =
        cards.values.forEach { addCard(it) }

    companion object {
        const val CARD_SETTING_COUNT = 2
        const val MAX_SUM_NUMBER = 21
    }
}
