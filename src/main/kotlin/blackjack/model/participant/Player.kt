package blackjack.model.participant

import blackjack.model.deck.Card
import blackjack.model.deck.HandCards

class Player(val name: String) : GameParticipant(HandCards()) {
    override fun add(cards: List<Card>): Boolean =
        if (!isBust()) {
            handCards.add(cards)
            true
        } else {
            false
        }
}
