package blackjack.model.participant

import blackjack.model.card.Card
import blackjack.model.card.CardProvider
import blackjack.view.CardDecision

class Player(val name: PlayerName) : Role() {
    override fun decideMoreCard() = !isBlackjack() && !isBurst()

    fun takeTurn(
        cardProvider: CardProvider,
        cardDecision: CardDecision,
        endRoundAction: (Player) -> Unit,
    ) {
        while (decideMoreCard() && cardDecision.isMoreCardDecision(this)) {
            receiveCard(Card.provideCards(cardProvider))
            endRoundAction(this)
        }
    }
}
