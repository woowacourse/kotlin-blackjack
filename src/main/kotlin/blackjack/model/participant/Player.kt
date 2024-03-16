package blackjack.model.participant

import blackjack.model.card.Card
import blackjack.model.card.CardProvider
import blackjack.view.CardDecision

class Player(
    val name: PlayerName,
    val battingAmount: BattingAmount,
    handCards: HandCards = HandCards(),
) : Role(handCards) {
    lateinit var profitAmount: BattingAmount
        private set

    constructor(name: String, amount: Int) : this(PlayerName(name), BattingAmount(amount))

    constructor(name: String, amount: Int, handCards: List<Card>) : this(
        PlayerName(name),
        BattingAmount(amount),
        HandCards(handCards.toMutableList()),
    )

    override fun decideMoreCard() = !isMaxCardSum() && !isBurst()

    fun takeTurn(
        cardProvider: CardProvider,
        cardDecision: CardDecision,
        endRoundAction: (Player) -> Unit,
    ) {
        while (decideMoreCard() && cardDecision.hasMoreCardDecision(this)) {
            receiveCard(Card.provideCards(cardProvider))
            endRoundAction(this)
        }
    }

    fun profit(dealer: Dealer): BattingAmount {
        val cardSum = getCardSum()
        val dealerCardSum = dealer.getCardSum()

        when {
            isBlackjack() -> setProfitAmount(1.5)
            isBurst() -> setProfitAmount(-1.0)
            dealer.isBurst() -> setProfitAmount(1.0)
            cardSum > dealerCardSum -> setProfitAmount(1.0)
            cardSum == dealerCardSum -> setProfitAmount(0.0)
            else -> setProfitAmount(-1.0)
        }
        return profitAmount
    }

    private fun setProfitAmount(rate: Double) {
        profitAmount = battingAmount * rate
    }
}
