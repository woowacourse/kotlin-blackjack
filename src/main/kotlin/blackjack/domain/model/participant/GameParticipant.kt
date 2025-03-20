package blackjack.domain.model.participant

import blackjack.domain.model.card.Card
import blackjack.domain.model.card.CardStatus
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

    abstract val initCards: List<Card>

    private val cardSize: Int
        get() = handCards.cards.size

    protected fun winLoss(rival: GameParticipant): WinLoss = handCards.calculateWinLoss(rival.cardStatus, rival.bestValue)

    fun isInitHandCard() = cardSize == HandCards.INIT_CARD_SIZE

    fun drawCardFromDeck(deck: Deck) {
        handCards.addCard(deck.getCard())
    }

    abstract fun isDrawFinish(): Boolean
}
