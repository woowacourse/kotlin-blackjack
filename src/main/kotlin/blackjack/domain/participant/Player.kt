package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.money.Money
import blackjack.domain.result.GameResult
import blackjack.domain.state.CardState
import blackjack.domain.state.StartState

class Player(
    name: String,
    cardState: CardState = StartState(),
    private val money: Money,
    val needToDraw: () -> Boolean = { true }
) : Participant(name, cardState) {

    override fun getFirstOpenCards(): List<Card> = getCards()

    override fun stay(): Participant = Player(name, cardState.stay(), money, needToDraw)

    override fun draw(card: Card): Participant = Player(name, cardState.draw(card), money, needToDraw)

    override fun getProfit(other: Participant): Money {
        if (!isBust() && other.isBust()) return money
        else if (isBust() && !other.isBust()) return Money(0)
        else if (isBust() && other.isBust()) return -money
        else if (isBlackjack() && other.isBlackjack()) return Money(0)
        else if (isBlackjack() && !other.isBlackjack()) return cardState.profit(money)
        return getProfitWhenStay(other)
        // else if (isStay() && other.isStay()) return getProfitWhenStay(other)
        // throw IllegalArgumentException("수익을 계산할 수 없습니다!")
    }

    private fun getProfitWhenStay(other: Participant): Money = when (compareScore(other)) {
        GameResult.WIN -> cardState.profit(money)
        GameResult.DRAW -> Money(0)
        GameResult.LOSE -> -cardState.profit(money)
    }
}
