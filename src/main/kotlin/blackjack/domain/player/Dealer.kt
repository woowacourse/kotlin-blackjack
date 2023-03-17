package blackjack.domain.player

import blackjack.domain.card.Cards

class Dealer(
    name: String = "딜러",
    cards: Cards = Cards()
) : Player(name, cards) {

    override fun canHit(): Boolean = cards.sum() <= MIN_SUM_NUMBER

    fun getPayout(participants: Participants): Int {
        var sum = 0
        participants.values.forEach { sum += it.bettingAmount.getPayout(it.matchResult.getUniqueCountResult()) }
        return sum * (-1)
    }

    companion object {
        const val MIN_SUM_NUMBER = 16
    }
}
