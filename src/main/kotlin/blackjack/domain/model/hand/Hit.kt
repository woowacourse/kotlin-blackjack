package blackjack.domain.model.hand

import blackjack.domain.model.Card

class Hit(
    override val stayStrategy: StayStrategy,
    hands: Hands = Hands(),
) : Playing(hands) {
    override fun nextState(card: Card): State {
        hands = hands.nextHand(card)
        return when {
            hands.isBlackJack() -> BlackJack(hands)
            hands.isBustScore() -> Bust(hands)
            stayStrategy.isStay(score()) -> Stay(hands)
            else -> this
        }
    }
}
