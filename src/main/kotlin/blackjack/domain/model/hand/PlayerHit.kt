package blackjack.domain.model.hand

import blackjack.domain.model.Card

class PlayerHit(hands: Hands = Hands()) : Playing(hands) {
    override fun nextState(card: Card): State {
        hands = hands.nextHand(card)
        return when {
            hands.score().isMaxScore() && hands.score().isBlackJack(hands.size) -> BlackJack(hands)
            hands.score().isBustScore() -> Bust(hands)
            hands.score().isMaxScore() -> Stay(hands)
            else -> this
        }
    }
}
