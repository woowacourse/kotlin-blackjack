package blackjack.domain.player

import blackjack.domain.BettingAmount
import blackjack.domain.card.Cards

class Participant(
    name: String,
    cards: Cards = Cards()
) : Player(name, cards) {

    @set:JvmName("bettingAmount")
    lateinit var bettingAmount: BettingAmount

    override fun canHit(): Boolean = cards.sum() < MAX_SUM_NUMBER

    fun setBettingAmount(newBettingAmount: BettingAmount) {
        this.bettingAmount = newBettingAmount
    }
}
