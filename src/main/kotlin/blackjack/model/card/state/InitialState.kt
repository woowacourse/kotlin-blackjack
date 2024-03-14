package blackjack.model.card.state

import blackjack.model.card.Card
import blackjack.model.card.CardHand
import blackjack.model.config.GameRule.INITIAL_CARD_COUNT

class InitialState(cardHand: CardHand) : Decide(cardHand) {
    override fun draw(card: Card): CardsState {
        cardHand.addNewCard(card)
        if (cardHand.hand.size < INITIAL_CARD_COUNT) {
            return InitialState(cardHand)
        }
        return Hit(cardHand)
    }

    override fun stay(): CardsState = throw IllegalStateException("현재 상태에서는 stay 선언을 할 수 없습니다.")
}
