package blackjack.domain.model.hand

import blackjack.domain.model.Card

class PlayerHit(hands: Hands = Hands()) : Playing(hands) {
    override fun nextState(card: Card): State {
        hands = hands.nextHand(card)
        return when {
            hands.getScore() == 21 && hands.size == 2 -> BlackJack(hands)
            hands.getScore() > 21 -> Bust(hands)
            hands.getScore() == 21 -> Stay(hands)
            else -> this
        }
    }
}
