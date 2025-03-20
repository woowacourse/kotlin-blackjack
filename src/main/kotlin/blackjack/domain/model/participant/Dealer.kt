package blackjack.domain.model.participant

import blackjack.domain.model.card.Card
import blackjack.domain.model.participant.bet.Profit

class Dealer(
    name: String = DEFAULT_NAME,
) : GameParticipant(name = name) {
    override val initCards: List<Card>
        get() = cards.subList(0, 1)

    constructor(cards: List<Card>) : this() {
        cards.forEach { card -> handCards.addCard(card) }
    }

    override fun isDrawFinish(): Boolean {
        val bestCardValue = handCards.calculateBestCardValue()
        return bestCardValue <= DEALER_DRAW_LIMIT
    }

    fun allPlayersMatchProfit(players: Collection<Player>): Profit {
        val playersProfitSum = players.sumOf { player -> player.dealerMatchProfit(this).value }
        return Profit(-playersProfitSum + 0.0)
    }

    companion object {
        private const val DEFAULT_NAME = "딜러"
        private const val DEALER_DRAW_LIMIT = 16
    }
}
