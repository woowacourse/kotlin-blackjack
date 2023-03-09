package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CalculatorTest {
    @Test
    fun `1000원을 베팅하고 승리하면 2000원을 받는다`() {
        // given
        val card1 = Card.get(Shape.HEART, CardNumber.TWO)
        val card2 = Card.get(Shape.HEART, CardNumber.THREE)
        val cardBunch = CardBunch(card1, card2)
        val player = Player("krrong", cardBunch, BettingMoney(1000))
        val calculator = Calculator()

        // when
        val actual = calculator.calculateDividend(player, Consequence.WIN)

        // then
        assertThat(actual).isEqualTo(2000)
    }

    @Test
    fun `1000원을 베팅하고 패배하면 0원을 받는다`() {
        // given
        val card1 = Card.get(Shape.HEART, CardNumber.TWO)
        val card2 = Card.get(Shape.HEART, CardNumber.THREE)
        val cardBunch = CardBunch(card1, card2)
        val player = Player("krrong", cardBunch, BettingMoney(1000))
        val calculator = Calculator()

        // when
        val actual = calculator.calculateDividend(player, Consequence.LOSE)

        // then
        assertThat(actual).isEqualTo(0)
    }

    @Test
    fun `1000원을 베팅하고 비기면 1000원을 받는다`() {
        // given
        val card1 = Card.get(Shape.HEART, CardNumber.TWO)
        val card2 = Card.get(Shape.HEART, CardNumber.THREE)
        val cardBunch = CardBunch(card1, card2)
        val player = Player("krrong", cardBunch, BettingMoney(1000))
        val calculator = Calculator()

        // when
        val actual = calculator.calculateDividend(player, Consequence.DRAW)

        // then
        assertThat(actual).isEqualTo(1000)
    }
}
