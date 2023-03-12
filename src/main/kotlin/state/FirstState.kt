package state

import domain.BlackJackGame
import domain.card.Card

class FirstState(val cards: List<Card>) : State {
    override fun next(card: Card): State {
        val nextCards = cards.toMutableList()
        nextCards.add(card)

        if (nextCards.size == 2) {
            var sum = nextCards.sumOf { it.cardNumber.value }
            if (nextCards.any { it.isAce }) sum += 10
            if (sum == BlackJackGame.BLACKJACK_NUMBER)
                return BlackJackState(nextCards)
            else
                return HitState(nextCards)
        }
        return FirstState(nextCards)
    }
}
