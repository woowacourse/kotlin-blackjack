package blackjack.domain.model

import blackjack.domain.model.Verdict.DRAW
import blackjack.domain.model.Verdict.LOSE
import blackjack.domain.model.Verdict.WIN

class Dealer(override val hands: Hands = Hands()) : Participant() {
    constructor(vararg card: Card) : this(Hands(*card))

    override val name: String = DEALER_NAME

    fun getPlayerVerdict(players: List<Participant>): Map<Participant, Verdict> {
        return players.associateWith { player -> determine(player.hands).reverse() }
    }

    fun getDealerVerdicts(players: List<Participant>): Map<Verdict, Int> {
        return Verdict.entries.associateWith { verdict ->
            players.count { verdict == determine(it.hands) }
        }
    }

    private fun determine(otherHands: Hands): Verdict {
        return when {
            hands.isBust() && otherHands.isBust() -> WIN
            hands.isBust() -> LOSE
            hands.getScore() > otherHands.getScore() || otherHands.isBust() -> WIN
            hands.getScore() < otherHands.getScore() && !otherHands.isBust() -> LOSE
            else -> DRAW
        }
    }

    companion object {
        const val DEALER_NAME = "딜러"
        const val DEALER_DRAW_THRESHOLD = 16
    }
}
