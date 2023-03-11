package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

abstract class Player(
    val name: String,
    val cards: Cards
) {
    val isBurst
        get() = cards.isBust()
    val isBlackjack
        get() = cards.isBlackjack()

    init {
        require(name.length in 2..10) { ERROR_NAME_LENGTH }
    }

    abstract fun checkProvideCardPossible(): Boolean

    fun addCard(card: Card) {
        cards.addCard(card)
    }

    companion object {
        const val ERROR_NAME_LENGTH = "이름은 2글자 이상 10글자 이하여야 합니다."
    }
}
