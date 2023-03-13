package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.money.BetMoney
import blackjack.domain.money.Money
import blackjack.domain.state.CardState
import blackjack.domain.state.StartState

class Player(
    name: String,
    cardState: CardState = StartState(),
    private val money: BetMoney,
    val needToDraw: () -> Boolean = { true }
) : Participant(name, cardState) {
    override val maxDrawableScore: Int = 21

    override fun getFirstOpenCards(): List<Card> = getCards()

    override fun stay(): Participant = Player(name, cardState.stay(), money, needToDraw)

    override fun canDraw(): Boolean = needToDraw() && cardState.getTotalScore() <= maxDrawableScore

    override fun draw(card: Card, justDraw: Boolean): Participant {
        if (justDraw || canDraw()) {
            return Player(name, cardState.draw(card), money, needToDraw)
        }
        return stay()
    }

    override fun getProfit(others: List<Participant>): Money {
        val dealer = others.first { it is Dealer }
        return cardState.profit(dealer.cardState, money)
    }
}
