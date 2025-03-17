package blackjack.domain.model.hand

import blackjack.domain.model.Card

class PlayerHit(hands: Hands = Hands()) : Playing(hands) {
    override fun nextState(card: Card): State {
        hands = hands.nextHand(card)
        return when {
            hands.isMaxScore() && hands.isBlackJack(hands.size) -> BlackJack(hands)
            hands.isBustScore() -> Bust(hands)
            hands.isMaxScore() -> Stay(hands)
            else -> this
        }
    }
}
