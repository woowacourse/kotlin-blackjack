package blackjack.model

class Dealer(
    val name: String = DEALER_NAME,
    override val items: Items,
) : Participant {
    tailrec fun drawUntilFinished(cardDeck: CardDeck) {
        if (items.hand.score() > DEALER_DRAW_CRITERIA || items.hand.isBust()) return
        draw(cardDeck.draw())
        drawUntilFinished(cardDeck)
    }

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val DEALER_DRAW_CRITERIA = 16

        fun makeDealer(hand: Hand): Dealer = Dealer(DEALER_NAME, Items(hand, Money(0.0)))
    }
}
