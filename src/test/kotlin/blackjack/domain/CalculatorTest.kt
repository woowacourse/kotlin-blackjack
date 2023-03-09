package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CalculatorTest {
    @Test
    fun `1000원을 베팅하고 승리하면 1000원 이익이다`() {
        // given
        val card1 = Card.get(Shape.HEART, CardNumber.TWO)
        val card2 = Card.get(Shape.HEART, CardNumber.THREE)
        val cardBunch = CardBunch(card1, card2)
        val player = Player("krrong", cardBunch, BettingMoney(1000))
        val calculator = Calculator()

        // when
        val actual = calculator.calculateDividend(mapOf(player to Consequence.WIN))

        // then
        assertThat(actual[player]).isEqualTo(1000)
    }

    @Test
    fun `1000원을 베팅하고 지면 -1000원 이득이다`() {
        // given
        val card1 = Card.get(Shape.HEART, CardNumber.TWO)
        val card2 = Card.get(Shape.HEART, CardNumber.THREE)
        val cardBunch = CardBunch(card1, card2)
        val player = Player("krrong", cardBunch, BettingMoney(1000))
        val calculator = Calculator()

        // when
        val actual = calculator.calculateDividend(mapOf(player to Consequence.LOSE))

        // then
        assertThat(actual[player]).isEqualTo(-1000)
    }

    @Test
    fun `1000원을 베팅하고 비기면 0원 이득이다`() {
        // given
        val card1 = Card.get(Shape.HEART, CardNumber.TWO)
        val card2 = Card.get(Shape.HEART, CardNumber.THREE)
        val cardBunch = CardBunch(card1, card2)
        val player = Player("krrong", cardBunch, BettingMoney(1000))
        val calculator = Calculator()

        // when
        val actual = calculator.calculateDividend(mapOf(player to Consequence.DRAW))

        // then
        assertThat(actual[player]).isEqualTo(0)
    }
}
