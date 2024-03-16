package blackjack.model.participant

import blackjack.model.card.Card
import blackjack.model.card.CardProvider

class Dealer(handCards: HandCards = HandCards()) : Role(handCards) {
    constructor(handCards: List<Card>) : this(HandCards(handCards.toMutableList()))

    override fun decideMoreCard() = score() < MIN_CARD_SUM

    fun takeTurn(
        cardProvider: CardProvider,
        endRoundAction: () -> Unit,
    ) {
        while (decideMoreCard()) {
            receiveCard(Card.provideCards(cardProvider))
            endRoundAction()
        }
    }

    companion object {
        private const val MIN_CARD_SUM = 17
    }
}
