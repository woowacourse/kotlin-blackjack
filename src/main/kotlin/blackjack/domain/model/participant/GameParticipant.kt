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
        get() = handCards.status

    val bestValue: Int
        get() = handCards.bestCardValue

    val cards: List<Card>
        get() = handCards.cards.toList()

    val isInitHandCard: Boolean
        get() = cardSize == HandCards.INIT_CARD_SIZE

    private val cardSize: Int
        get() = handCards.cards.size

    abstract val initCards: List<Card>

    abstract val isDrawFinish: Boolean

    protected fun winLoss(rival: GameParticipant): WinLoss = handCards.versusRivalWinLoss(rival.cardStatus, rival.bestValue)

    fun fromDeckCardDraw(deck: Deck) {
        handCards.cardAdd(deck.popCard())
    }
}
