package blackjack.domain.model.participant

import blackjack.domain.model.card.Card
import blackjack.domain.model.participant.bet.BetAmount
import blackjack.domain.model.participant.bet.Profit

class Dealer(
    participantInfo: ParticipantInfo = ParticipantInfo(DEFAULT_NAME, BetAmount()),
) : GameParticipant(participantInfo = participantInfo) {
    override val initCards: List<Card>
        get() = cards.subList(0, 1)

    constructor(cards: List<Card>) : this() {
        cards.forEach { card -> handCards.addCard(card) }
    }

    override fun isDrawFinish(): Boolean {
        val bestCardValue = handCards.calculateBestCardValue()
        return bestCardValue <= DEALER_DRAW_LIMIT
    }

    fun calculateProfit(players: Collection<Player>): Profit {
        val playersProfitSum = players.sumOf { player -> player.calculateProfit(this).value }
        return Profit(-playersProfitSum + 0.0)
    }

    companion object {
        private const val DEFAULT_NAME = "딜러"
        private const val DEALER_DRAW_LIMIT = 16
    }
}
