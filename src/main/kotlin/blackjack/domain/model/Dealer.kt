package blackjack.domain.model

import blackjack.domain.model.Verdict.DRAW
import blackjack.domain.model.Verdict.LOSE
import blackjack.domain.model.Verdict.WIN

class Dealer(override val cards: Cards = Cards()) : Participant() {
    constructor(vararg card: Card) : this(Cards(*card))

    override val name: String = DEALER_NAME

    fun getPlayerVerdict(players: List<Participant>): Map<Participant, Verdict> {
        return players.associateWith { player -> determine(player.cards).reverse() }
    }

    fun getDealerVerdicts(players: List<Participant>): Map<Verdict, Int> {
        return Verdict.entries.associateWith { verdict ->
            players.count { verdict == determine(it.cards) }
        }
    }

    private fun determine(otherCards: Cards): Verdict {
        return when {
            cards.isBust() && otherCards.isBust() -> WIN
            cards.isBust() -> LOSE
            cards.getScore() > otherCards.getScore() || otherCards.isBust() -> WIN
            cards.getScore() < otherCards.getScore() && !otherCards.isBust() -> LOSE
            else -> DRAW
        }
    }

    companion object {
        const val DEALER_NAME = "딜러"
        const val DEALER_DRAW_THRESHOLD = 16
    }
}
