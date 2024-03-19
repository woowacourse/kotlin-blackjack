package blackjack.model.gameInfo

import blackjack.model.card.Card

class GameInfo(
    val name: String,
    val moneyAmount: Money = Money(),
    cards: Set<Card> = emptySet(),
) {
    private val _cards: MutableSet<Card> = cards.toMutableSet()
    val cards: Set<Card>
        get() = _cards

    val sumOfCards: Int
        get() = cards.sumOf { it.value }

    fun addCard(card: Card) {
        _cards.add(card)
    }

    override fun toString(): String {
        return MESSAGE_PARTICIPANT_CARD_RESULT.format(
            name,
            cards.joinToString { MESSAGE_CARD_INFO.format(it.title, it.shape.title) },
            sumOfCards,
        )
    }

    companion object {
        private const val MESSAGE_PARTICIPANT_CARD_RESULT = "%s카드: %s - 결과: %d"
        private const val MESSAGE_CARD_INFO = "%s%s"
    }
}
