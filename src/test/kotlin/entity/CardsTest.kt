package entity

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {
    @Test
    fun `3이 세장 있으면 카드 숫자 개수의 합은 9이다`() {
        // given
        val cards = Cards(
            listOf(
                Card(CardType.SPADE, CardNumber.THREE),
                Card(CardType.SPADE, CardNumber.THREE),
                Card(CardType.SPADE, CardNumber.THREE)
            )
        )

        // when
        val actual = cards.sumOfNumbers()
        val except = 9

        // then
        assertThat(actual).isEqualTo(except)
    }
}