package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.money.Money
import blackjack.domain.state.CardState
import blackjack.domain.state.StartState

class Dealer(cardState: CardState = StartState()) : Participant(DEALER_NAME, cardState) {
    override fun getFirstOpenCards(): List<Card> = listOf(getFirstCard())

    override fun stay(): Participant = Dealer(cardState.stay())

    override fun draw(card: Card): Participant = Dealer(cardState.draw(card))

    /**
     플레이어 손익의 반대이다.
     */
    override fun getProfit(other: Participant): Money = -other.getProfit(this)

    companion object {
        private const val DEALER_NAME = "딜러"
    }
}
