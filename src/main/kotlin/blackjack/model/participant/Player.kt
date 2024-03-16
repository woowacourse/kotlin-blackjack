package blackjack.model.participant

import blackjack.constants.GameResult
import blackjack.model.card.Card
import blackjack.model.card.CardProvider
import blackjack.view.CardDecision

class Player(
    val name: PlayerName,
    val battingAmount: Amount,
    handCards: HandCards = HandCards(),
) : Role(handCards) {
    constructor(name: String, amount: Int) : this(PlayerName(name), Amount(amount))

    constructor(name: String, amount: Int, handCards: List<Card>) : this(
        PlayerName(name),
        Amount(amount),
        HandCards(handCards.toMutableList()),
    )

    override fun receivableMoreCard() = !isMaxCardSum() && !isBurst()

    fun takeTurn(
        cardProvider: CardProvider,
        cardDecision: CardDecision,
        endRoundAction: (Player) -> Unit,
    ) {
        while (receivableMoreCard() && cardDecision.hasMoreCardDecision(this)) {
            receiveCard(Card.provideCards(cardProvider))
            endRoundAction(this)
        }
    }

    fun profit(dealer: Dealer): Amount {
        return battingAmount * gameResult(dealer).profitRate
    }

    private fun gameResult(dealer: Dealer): GameResult {
        return when {
            isBlackjack() -> GameResult.BLACKJACK_WIN
            isBurst() -> GameResult.LOSE
            dealer.isBurst() -> GameResult.WIN
            else -> super.gameResult(dealer)
        }
    }
}
