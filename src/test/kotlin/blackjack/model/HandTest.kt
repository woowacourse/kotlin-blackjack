package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HandTest {
    @Test
    fun `카드의 합이 기준점 이상일경우 bust다`() {
        val hand =
            Hand(
                listOf(
                    Card(CardNumber.EIGHT, CardShape.SPADE),
                    Card(CardNumber.NINE, CardShape.CLOVER),
                    Card(CardNumber.TEN, CardShape.DIAMOND),
                ),
            )

        assertThat(hand.isBust()).isTrue()
    }

    @Test
    fun `선택한 카드를 추가할 수 있다`() {
        val hand =
            Hand(
                listOf(
                    Card(CardNumber.EIGHT, CardShape.SPADE),
                    Card(CardNumber.NINE, CardShape.CLOVER),
                ),
            )
        hand.addCard(Card(CardNumber.TEN, CardShape.DIAMOND))

        assertThat(hand.cards).isEqualTo(
            listOf(
                Card(CardNumber.EIGHT, CardShape.SPADE),
                Card(CardNumber.NINE, CardShape.CLOVER),
                Card(CardNumber.TEN, CardShape.DIAMOND),
            ),
        )
    }
}
