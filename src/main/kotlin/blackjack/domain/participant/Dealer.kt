package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.state.CardState
import blackjack.domain.state.StartState

class Dealer(cardState: CardState = StartState()) : Participant(DEALER_NAME, cardState) {
    override fun getFirstOpenCards(): List<Card> = listOf(getFirstCard())

    // override fun canDraw(): Boolean = !cardState.isFinished

    override fun stay(): Participant = Dealer(cardState)

    override fun draw(card: Card): Participant = Dealer(cardState.draw(card))

    companion object {
        private const val DEALER_NAME = "딜러"
    }
}
