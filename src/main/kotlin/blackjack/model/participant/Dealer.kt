package blackjack.model.participant

import blackjack.model.card.Card
import blackjack.model.card.CardProvider

class Dealer(handCards: HandCards = HandCards()) : Role(handCards) {
    constructor(handCards: List<Card>) : this(HandCards(handCards.toMutableList()))

    override fun receivableMoreCard() = score() < MIN_CARD_SUM

    fun takeTurn(
        cardProvider: CardProvider,
        endRoundAction: () -> Unit,
    ) {
        while (receivableMoreCard()) {
            receiveCard(Card.provideCards(cardProvider))
            endRoundAction()
        }
    }

    fun profit(players: Players): Amount {
        return players.playerGroup.fold(Amount(0)) { acc, player ->
            acc - player.profit(this)
        }
    }

    companion object {
        private const val MIN_CARD_SUM = 17
    }
}
