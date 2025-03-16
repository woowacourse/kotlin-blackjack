package blackjack.domain.model.participant

import blackjack.domain.model.card.Card
import blackjack.domain.model.card.CardStatus
import blackjack.domain.model.participant.bet.BetAmount
import blackjack.domain.model.participant.bet.Profit
import blackjack.domain.model.participant.bet.ProfitRate

class Player(
    playerInfo: ParticipantInfo = ParticipantInfo(DEFAULT_NAME, BetAmount()),
) : GameParticipant(participantInfo = playerInfo) {
    override val initCards: List<Card>
        get() = cards.subList(0, 2)

    override fun isDrawFinish(): Boolean = this.cardStatus == CardStatus.BUST

    constructor(name: String = DEFAULT_NAME, cards: List<Card> = emptyList()) : this(ParticipantInfo(name, BetAmount())) {
        cards.forEach { card -> this.handCards.addCard(card) }
    }

    fun calculateProfit(dealer: Dealer): Profit {
        val profitRate = ProfitRate.calculateProfitRate(this.calculateWinLoss(dealer), this.cardStatus)
        return Profit(profitRate.value * this.betAmount.value)
    }

    companion object {
        private const val DEFAULT_NAME = "이름 없음"
    }
}
