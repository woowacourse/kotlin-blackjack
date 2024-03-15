package blackjack.model.card.state

import blackjack.model.card.Card
import blackjack.model.card.CardHand

class InitialState(cardHand: CardHand) : Decide(cardHand) {
    override fun draw(card: Card): CardHandState {
        cardHand.addNewCard(card)
        return when {
            cardHand.isReady -> InitialState(cardHand)
            cardHand.isBlackJack -> BlackJack(cardHand)
            else -> Hit(cardHand)
        }
    }

    override fun stay(): CardHandState = throw IllegalStateException("현재 상태에서는 stay 선언을 할 수 없습니다.")
}
