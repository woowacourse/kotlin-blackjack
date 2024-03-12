package blackjack.model.card.state

import blackjack.model.card.Card
import blackjack.model.card.CardHand

abstract class Done(val cardHand: CardHand) : CardsState {
    override fun draw(card: Card): CardsState = throw IllegalStateException("예기치 못한 오류")

    override fun stay(): CardsState = throw IllegalStateException("예기치 못한 오류")

    override fun getCardHands(): CardHand = cardHand

    override fun countCards(): Int = cardHand.hand.size
}
