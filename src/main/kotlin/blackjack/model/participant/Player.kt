package blackjack.model.participant

import blackjack.model.card.Card
import blackjack.model.card.CardProvider
import blackjack.view.CardDecision

class Player(
    val name: PlayerName,
    val battingAmount: BattingAmount,
    handCards: HandCards = HandCards(),
) : Role(handCards) {
    constructor(name: String, amount: Int) : this(PlayerName(name), BattingAmount(amount))

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
}
