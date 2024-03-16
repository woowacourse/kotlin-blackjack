package blackjack.model.participant

import blackjack.model.card.Card
import blackjack.model.card.CardProvider
import blackjack.view.CardDecision

class Player(
    val name: PlayerName,
    val battingAmount: Amount,
    handCards: HandCards = HandCards(),
) : Role(handCards) {
    lateinit var profitAmount: Amount
        private set

    constructor(name: String, amount: Int) : this(PlayerName(name), Amount(amount))

    constructor(name: String, amount: Int, handCards: List<Card>) : this(
        PlayerName(name),
        Amount(amount),
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

    fun profit(dealer: Dealer): Amount {
        val score = score()
        val dealerScore = dealer.score()

        when {
            isBlackjack() -> setProfitAmount(1.5)
            isBurst() -> setProfitAmount(-1.0)
            dealer.isBurst() -> setProfitAmount(1.0)
            score > dealerScore -> setProfitAmount(1.0)
            score == dealerScore -> setProfitAmount(0.0)
            else -> setProfitAmount(-1.0)
        }
        return profitAmount
    }

    private fun setProfitAmount(rate: Double) {
        profitAmount = battingAmount * rate
    }
}
