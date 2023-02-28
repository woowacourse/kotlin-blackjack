package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CardsTest {
    @Test
    fun `카드의 개수는 2장 이상이어야 한다`() {
        // given

        // when

        // then
        assertThrows<IllegalStateException> { Cards(listOf<Card>(Card(CardCategory.CLOVER, CardNumber.ACE))) }
    }

    @Test
    fun `카드 번호의 총합을 계산한다`() {
        val cards =
            Cards(
                listOf<Card>(
                    Card(CardCategory.CLOVER, CardNumber.TWO),
                    Card(CardCategory.CLOVER, CardNumber.KING)
                )
            )
        val actual = cards.sum()
        val expected = 12
        assertThat(actual).isEqualTo(expected)
    }
}
