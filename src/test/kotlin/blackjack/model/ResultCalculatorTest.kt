package blackjack.model

import blackjack.model.card.Card
import blackjack.model.card.CardNumber
import blackjack.model.card.Shape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ResultCalculatorTest {
    private fun fakeCards(vararg cards: Card): List<Card> = cards.map { it }

    @Test
    fun `ACE를 11로 계산할 수 있을 때 11로 처리한다`() {
        val cards =
            fakeCards(
                Card(Shape.SPADE, CardNumber.TWO),
                Card(Shape.CLOVER, CardNumber.THREE),
                Card(Shape.DIAMOND, CardNumber.ACE),
            )
        val expect = 16

        val actual = ResultCalculator.calculate(cards)

        assertThat(actual).isEqualTo(expect)
    }

    @Test
    fun `ACE를 11로 계산할 수 없을 때 1로 처리한다`() {
        val cards =
            fakeCards(
                Card(Shape.SPADE, CardNumber.TEN),
                Card(Shape.CLOVER, CardNumber.TEN),
                Card(Shape.DIAMOND, CardNumber.ACE),
            )
        val expect = 21

        val actual = ResultCalculator.calculate(cards)

        assertThat(actual).isEqualTo(expect)
    }
}
