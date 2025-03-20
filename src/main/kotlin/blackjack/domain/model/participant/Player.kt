package blackjack.domain.model.participant

import blackjack.domain.model.card.Card
import blackjack.domain.model.card.CardStatus
import blackjack.domain.model.participant.bet.BetAmount
import blackjack.domain.model.participant.bet.Profit
import blackjack.domain.model.participant.bet.ProfitRate

class Player(
    name: String = DEFAULT_NAME,
    private val betAmount: BetAmount,
) : GameParticipant(name = name) {
    constructor(betAmount: BetAmount, cards: List<Card>) : this(betAmount = betAmount) {
        cards.forEach { handCards.cardAdd(it) }
    }

    override val initCards: List<Card>
        get() = cards.subList(0, 2)

    override val isDrawFinish: Boolean
        get() = this.cardStatus == CardStatus.BUST

    fun dealerMatchProfit(dealer: Dealer): Profit {
        val profitRate = ProfitRate.calculateProfitRate(this.winLoss(dealer), this.cardStatus)
        return betAmount.calculateProfit(profitRate)
    }

    companion object {
        private const val DEFAULT_NAME = "이름 없음"
    }
}
