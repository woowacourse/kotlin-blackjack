package model

import model.Cards.Companion.DEALER_STANDARD_HIT_POINT

class Dealer(name: Name = Name(DEALER)) : Participant(name) {
    override fun getFirstOpenCards(): Cards = Cards(setOf(cards.firstCard()))

    override fun isPossibleDrawCard(): Boolean = cards.sum() <= DEALER_STANDARD_HIT_POINT

    override fun isHit(needToDraw: (String) -> Boolean): Boolean = isPossibleDrawCard()

    override fun getProfitMoney(other: Participant): Profit {
        if (other.isBust()) return Profit.of((other as Player).money, CardGameResult.WIN)
        if (isBust()) return Profit(DEALER_LOSE_PROFIT)
        if (cards.sum() > other.cards.sum()) return Profit.of((other as Player).money, CardGameResult.WIN)
        return Profit(DEALER_LOSE_PROFIT)
    }

    fun calculateDealerProfit(participants: Participants): Result {
        var profit = 0L
        participants.players.forEach { profit += getProfitMoney(it).value }
        return Result(this, Profit(profit))
    }

    companion object {
        const val DEALER = "딜러"
        private const val DEALER_LOSE_PROFIT = 0L
    }
}
