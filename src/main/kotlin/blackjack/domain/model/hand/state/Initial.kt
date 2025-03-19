package blackjack.domain.model.hand.state

import blackjack.domain.model.Card
import blackjack.domain.model.hand.Hands
import blackjack.domain.model.hand.strategy.StayStrategy

class Initial(private val stayStrategy: StayStrategy, hands: Hands) : BaseState(hands) {
    override fun nextState(card: Card): State {
        hands = hands.nextHand(card)
        return when {
            hands.isBlackJack() -> BlackJack(hands)
            stayStrategy.isStay(score()) -> Stay(hands)
            hands.isStartHandsCount() -> Hit(stayStrategy, hands)
            else -> this
        }
    }

    override fun stay(): Finished = Stay(hands)

    override fun isStarted(): Boolean = false

    override fun isFinished(): Boolean = false
}
