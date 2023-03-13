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
    override fun getFirstOpenCards(): List<Card> = getCards().take(FIRST_OPEN_CARDS_SIZE)

    override fun stay(): Participant = Player(name, cardState.stay(), money, needToDraw)

    override fun canDraw(): Boolean = needToDraw() && cardState.getTotalScore() <= MAX_DRAWABLE_SCORE

    override fun draw(card: Card, justDraw: Boolean): Participant {
        if (justDraw || canDraw()) {
            return Player(name, cardState.draw(card), money, needToDraw)
        }
        return stay()
    }

    override fun getProfit(others: List<Participant>): Money {
        val dealer = others.first { it !is Player }
        return cardState.profit(dealer.cardState, money)
    }

    companion object {
        private const val FIRST_OPEN_CARDS_SIZE = 2
        private const val MAX_DRAWABLE_SCORE: Int = 21
    }
}
