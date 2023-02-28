package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러가 소지한 카드의 합은 8이다`() {
        // given
        val dealer = Dealer(
            mutableListOf<Card>(
                Card(Card.Shape.CLUBS, Card.Value.FIVE),
                Card(Card.Shape.DIAMONDS, Card.Value.THREE),
            ),
        )

        // when
        val actual = dealer.calculateCardValueSum()

        // then
        assertThat(actual).isEqualTo(8)
    }

    @Test
    fun `2장의 합이 16이하일 경우, 카드 1장을 추가로 받는다`() {
        // given
        val dealer = Dealer(
            mutableListOf<Card>(
                Card(Card.Shape.CLUBS, Card.Value.FIVE),
                Card(Card.Shape.DIAMONDS, Card.Value.THREE),
            ),
        )

        // when
        dealer.pickNewCard()
        val actual = dealer.cards

        // then
        assertThat(actual).hasSize(3)
    }

    @Test
    fun `2장의 합이 17이상인 경우, 카드를 받지 않는다`() {
        // given
        val dealer = Dealer(
            mutableListOf<Card>(
                Card(Card.Shape.CLUBS, Card.Value.JACK),
                Card(Card.Shape.DIAMONDS, Card.Value.EIGHT),
            ),
        )

        // when
        dealer.isOverSumCondition()
        val actual = dealer.cards

        // then
        assertThat(actual).hasSize(2)
    }
}
