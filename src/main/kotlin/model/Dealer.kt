package model

import model.Cards.Companion.DEALER_STANDARD_HIT_POINT

class Dealer(cards: Cards, name: Name = Name(DEALER)) : Participant(cards, name) {
    override fun getFirstOpenCards(): Cards = Cards(setOf(cards.firstCard()))

    override fun isHit(): Boolean = cards.sum() <= DEALER_STANDARD_HIT_POINT

    override fun getProfitMoney(other: Participant): Profit = -other.getProfitMoney(this)

    override fun isDealer(): Boolean = true

    override fun getResult(participants: Participants): Result {
        var profit = INITIALIZE_PROFIT
        participants.players.forEach { profit += getProfitMoney(it).value }
        return Result(this, Profit(profit))
    }

    companion object {
        const val DEALER = "딜러"
        private const val INITIALIZE_PROFIT = 0L
    }
}
