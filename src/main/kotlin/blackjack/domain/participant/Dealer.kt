package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.money.Money
import blackjack.domain.state.CardState
import blackjack.domain.state.StartState

class Dealer(cardState: CardState = StartState()) : Participant(DEALER_NAME, cardState) {
    override val maxDrawableScore: Int = 17

    override fun getFirstOpenCards(): List<Card> = listOf(getFirstCard())

    override fun stay(): Participant = Dealer(cardState.stay())

    override fun canDraw(): Boolean = cardState.getTotalScore() < maxDrawableScore

    override fun draw(card: Card, isFirstDraw: Boolean): Participant {
        if (isFirstDraw || canDraw()) {
            return Dealer(cardState = cardState.draw(card))
        }
        return stay()
    }

    /**
     플레이어 손익의 반대이다.
     */
    override fun getProfit(others: List<Participant>): Money {
        val players = others.filterIsInstance<Player>()
        return -Money(players.sumOf { it.getProfit(listOf(this)).getAmount() })
    }

    companion object {
        private const val DEALER_NAME = "딜러"
    }
}
