package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.card.Hand
import blackjack.domain.deck.Deck
import blackjack.domain.score.Score

abstract class Participant {
    protected val innerHand: Hand = Hand()
    abstract val hitThreshold: Int

    fun getCards(): List<Card> {
        return innerHand.toList()
    }

    fun addCard(card: Card) {
        if (canHit()) innerHand.add(card)
    }

    fun setInitialCard(deck: Deck) {
        repeat(INITIAL_CARD_COUNT) {
            addCard(deck.draw())
        }
    }

    fun isBust(): Boolean {
        return Score(this) > BLACKJACK_BUST_LIMIT
    }

    fun isBlackJack(): Boolean {
        return innerHand.countAce() == 1 && innerHand.countScoredTen() == 1
    }

    fun canHit(): Boolean {
        return Score(this) < hitThreshold
    }

    companion object {
        const val BLACKJACK_BUST_LIMIT = 21
        private const val INITIAL_CARD_COUNT = 2
    }
}
