package domain.state.playerState

import domain.card.Card
import domain.card.CardNumber
import domain.card.CardShape
import domain.card.Hand
import domain.money.Money
import domain.money.Profit
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class PlayerBlackJackTest {
    @Test
    fun `BlackJack 상태일 때 배당률은 일점오배 이다`() {
        val hand = Hand(Card(CardShape.HEART, CardNumber.ACE))
        val actual = PlayerFirstTurn(hand).draw(Card(CardShape.DIAMOND, CardNumber.TEN))

        val otherHand = Hand(Card(CardShape.HEART, CardNumber.ACE))
        val other = PlayerFirstTurn(otherHand).draw(Card(CardShape.DIAMOND, CardNumber.TEN))

        Assertions.assertThat(actual.profit(other, Money(10000))).isEqualTo(Profit(15000.0))
    }
}
