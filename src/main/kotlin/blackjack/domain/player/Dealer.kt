package blackjack.domain.player

import blackjack.domain.card.Cards
import blackjack.domain.result.MatchResult

class Dealer(
    name: String = "딜러",
    cards: Cards = Cards()
) : Player(name, cards) {

    override fun canHit(): Boolean = cards.sum() <= MIN_SUM_NUMBER

    fun getPayout(participants: Participants, participantsResults: List<MatchResult>): Int {
        var sum = 0
        participants.values.forEachIndexed { index, it ->
            sum += it.bettingAmount.getPayout(participantsResults[index].getUniqueCountResult())
        }
        return sum * (-1)
    }

    companion object {
        const val MIN_SUM_NUMBER = 16
    }
}
