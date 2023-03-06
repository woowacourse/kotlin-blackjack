package domain

import domain.card.Card
import domain.card.CardNumber
import domain.card.Shape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {

    @Test
    fun `2장의 합이 16이하일 경우, false 반환한다`() {
        // given
        val dealer = Dealer(
            cards = mutableListOf<Card>(
                Card(Shape.CLUBS, CardNumber.FIVE),
                Card(Shape.DIAMONDS, CardNumber.THREE),
            ),
        )

        // when
        val actual = dealer.isOverSumCondition()

        // then
        assertThat(actual).isEqualTo(false)
    }

    @Test
    fun `2장의 합이 17이상인 경우, true를 반환한다`() {
        // given
        val dealer = Dealer(
            cards = mutableListOf<Card>(
                Card(Shape.CLUBS, CardNumber.JACK),
                Card(Shape.DIAMONDS, CardNumber.EIGHT),
            ),
        )

        // when
        val actual = dealer.isOverSumCondition()

        // then
        assertThat(actual).isEqualTo(true)
    }
}
