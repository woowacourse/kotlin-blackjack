package blackjack.model.card.state

import blackjack.model.card.Card
import blackjack.model.card.CardHand
import blackjack.model.config.GameRule.BLACK_JACK_SCORE
import blackjack.model.config.GameRule.INITIAL_CARD_COUNT

class InitialState(cardHand: CardHand) : Decide(cardHand) {
    override fun draw(card: Card): CardHandState {
        cardHand.addNewCard(card)
        return when {
            cardHand.isReady() -> InitialState(cardHand)
            isBlackJack() -> BlackJack(cardHand)
            else -> Hit(cardHand)
        }
    }

    override fun stay(): CardHandState = throw IllegalStateException("현재 상태에서는 stay 선언을 할 수 없습니다.")

    private fun isBlackJack(): Boolean =
        (getCardHandScore() == BLACK_JACK_SCORE) && (countCards() == INITIAL_CARD_COUNT)
}
