package blackjack.model.card

import blackjack.model.HEART_KING

object TestCardProvider : CardProvider {
    override fun provide(
        cardBundle: List<Card>,
        cardCount: Int,
    ): List<Card> {
        return List(cardCount) { HEART_KING }
    }
}
