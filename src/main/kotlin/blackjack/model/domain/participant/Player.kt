package blackjack.model.domain.participant

import blackjack.model.domain.GameResult
import blackjack.model.domain.card.Card
import blackjack.model.domain.card.Hand

data class Player(override val name: String) : Participants() {
    override val hand: Hand = Hand(mutableListOf())

    override fun canHit(): Boolean {
        return !hand.isBust()
    }

    override fun getInitCard(): List<Card> {
        return hand.cards
    }

    fun compareScores(number: Int): GameResult {
        return if (!hand.isBust()) {
            GameResult.compare(sumCardNumber, number)
        } else {
            GameResult.Lose
        }
    }
}
