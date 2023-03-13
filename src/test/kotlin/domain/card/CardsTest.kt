package domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {
    @Test
    fun `카드 번호의 총합을 계산한다`() {
        // given
        val cards =
            Cards(
                listOf(
                    Card.of(CardCategory.CLOVER, CardNumber.TWO),
                    Card.of(CardCategory.CLOVER, CardNumber.KING),
                ),
            )

        // when
        val actual = cards.sum()
        val expected = 12

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `카드를 1개 추가하면 사이즈가 1 증가한다`() {
        // given
        val cards =
            Cards(
                listOf(
                    Card.of(CardCategory.CLOVER, CardNumber.TWO),
                    Card.of(CardCategory.CLOVER, CardNumber.KING),
                ),
            )
        val card = Card.of(CardCategory.DIAMOND, CardNumber.FIVE)

        // when
        cards.add(card)
        val expected = 3

        // then
        assertThat(cards.size).isEqualTo(expected)
    }
}