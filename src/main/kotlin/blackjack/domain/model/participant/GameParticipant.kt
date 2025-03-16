package blackjack.domain.model.participant

import blackjack.domain.model.card.Card
import blackjack.domain.model.card.Deck
import blackjack.domain.model.card.HandCards

abstract class GameParticipant(
    val name: String,
) {
    protected val handCards: HandCards = HandCards()

    val cardStatus: CardStatus
        get() = handCards.getStatus()

    val bestValue: Int
        get() = handCards.calculateBestCardValue()

    val cards: List<Card>
        get() = handCards.cards.toList()

    fun cardSize() = handCards.cards.size

    fun isInitHandCard() = handCards.cards.size == HandCards.INIT_CARD_SIZE

    fun drawCardFromDeck(deck: Deck) {
        handCards.addCard(deck.getCard())
    }

    abstract fun isDrawFinish(): Boolean
}
