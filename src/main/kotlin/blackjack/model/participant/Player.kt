package blackjack.model.participant

import blackjack.constants.GameResult
import blackjack.model.Amount
import blackjack.model.Hand
import blackjack.model.card.Card
import blackjack.model.card.CardProvider
import blackjack.view.CardDecision

class Player(
    val name: PlayerName,
    val battingAmount: Amount,
    hand: Hand = Hand(),
) : Role(hand) {
    constructor(name: String, amount: Int) : this(PlayerName(name), Amount(amount))

    constructor(name: String, amount: Int, cards: List<Card>) : this(
        PlayerName(name),
        Amount(amount),
        Hand(cards.toMutableList()),
    )

    override fun receivableMoreCard() = !isMaxScore() && !isBurst()

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
            isBlackjack() && dealer.isBlackjack() -> GameResult.DRAW
            isBlackjack() -> GameResult.BLACKJACK_WIN
            isBurst() -> GameResult.LOSE
            dealer.isBurst() -> GameResult.WIN
            else -> super.gameResult(dealer)
        }
    }
}
