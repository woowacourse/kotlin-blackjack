package model

import model.Cards.Companion.DEALER_STANDARD_HIT_POINT

class Dealer(cards: Cards, name: Name = Name(DEALER)) : Participant(cards, name) {
    override fun getFirstOpenCards(): Cards = Cards(setOf(cards.firstCard()))

    override fun isPossibleDrawCard(): Boolean = cards.sum() <= DEALER_STANDARD_HIT_POINT

    override fun isHit(): Boolean = isPossibleDrawCard()

    override fun getProfitMoney(other: Participant): Profit = -other.getProfitMoney(this)

    override fun isDealer(): Boolean = true

    fun calculateDealerProfit(participants: Participants): Result {
        var profit = 0L
        participants.players.forEach { profit += getProfitMoney(it).value }
        return Result(this, Profit(profit))
    }

    companion object {
        const val DEALER = "딜러"
    }
}
