package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {

    @Test
    fun `2장의 합이 16이하일 경우, false 반환한다`() {
        // given
        val dealer = Dealer(
            cards = mutableListOf<Card>(
                Card(Card.Shape.CLUBS, Card.Value.FIVE),
                Card(Card.Shape.DIAMONDS, Card.Value.THREE),
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
                Card(Card.Shape.CLUBS, Card.Value.JACK),
                Card(Card.Shape.DIAMONDS, Card.Value.EIGHT),
            ),
        )

        // when
        val actual = dealer.isOverSumCondition()

        // then
        assertThat(actual).isEqualTo(true)
    }
}
