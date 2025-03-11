package blackjack.domain.model

import blackjack.domain.model.Verdict.DRAW
import blackjack.domain.model.Verdict.LOSE
import blackjack.domain.model.Verdict.WIN

class Dealer(override var hands: Hands, override val name: String = DEALER_NAME) : Participant() {
    constructor(vararg card: Card) : this(Hands(card.toList()))

    override fun showInitCards(): List<Card> = showCards(INIT_VISIBLE_CARD_COUNT)

    fun isHit(): Boolean = getScore() <= DEALER_DRAW_THRESHOLD

    fun getPlayerVerdict(players: List<Participant>): Map<Participant, Verdict> {
        return players.associateWith { player -> determine(player).reverse() }
    }

    fun getDealerVerdicts(players: List<Participant>): Map<Verdict, Int> {
        return Verdict.entries.associateWith { verdict ->
            players.count { verdict == determine(it) }
        }
    }

    private fun determine(otherParticipant: Participant): Verdict {
        return when {
            this.isBust() && otherParticipant.isBust() -> WIN
            this.isBust() -> LOSE
            this.getScore() > otherParticipant.getScore() || otherParticipant.isBust() -> WIN
            this.getScore() < otherParticipant.getScore() && !otherParticipant.isBust() -> LOSE
            else -> DRAW
        }
    }

    companion object {
        private const val DEALER_NAME = "딜러"
        const val DEALER_DRAW_THRESHOLD = 16
        private const val INIT_VISIBLE_CARD_COUNT = 1
    }
}
