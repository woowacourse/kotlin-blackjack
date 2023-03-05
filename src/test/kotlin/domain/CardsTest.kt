package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CardsTest {
    @Test
    fun `카드의 개수는 2장 이상이어야 한다`() {
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

    @Test
    fun `카드를 1개 추가하면 사이즈가 1 증가한다`() {
        // given
        val cards =
            Cards(
                listOf<Card>(
                    Card(CardCategory.CLOVER, CardNumber.TWO),
                    Card(CardCategory.CLOVER, CardNumber.KING)
                )
            )
        val card = Card(CardCategory.DIAMOND, CardNumber.FIVE)

        // when
        cards.add(card)
        val expected = 3
        assertThat(cards.size).isEqualTo(expected)
    }

    @Test
    fun `카드를 1개 추가하면 카드목록이 그 카드를 포함한다`() {
        // given
        val cards =
            Cards(
                listOf<Card>(
                    Card(CardCategory.CLOVER, CardNumber.TWO),
                    Card(CardCategory.CLOVER, CardNumber.KING)
                )
            )
        val card = Card(CardCategory.DIAMOND, CardNumber.FIVE)

        // when
        cards.add(card)
        assertThat(cards.contains(card)).isTrue
    }

    @Test
    fun `에이스가 있다면 모두 1로 보고 더한 최소 합을 구한다`() {
        val cards =
            Cards(
                listOf<Card>(
                    Card(CardCategory.CLOVER, CardNumber.TWO),
                    Card(CardCategory.CLOVER, CardNumber.KING),
                    Card(CardCategory.CLOVER, CardNumber.ACE)
                )
            )

        val result = cards.minSumState()
        val expected = Cards.State.NoBurst(13)
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `에이스가 있다면 에이스 한 개를 11로 보고 더한 최대 합과 상태를 구한다`() {
        val cards =
            Cards(
                Card(CardCategory.CLOVER, CardNumber.KING),
                Card(CardCategory.CLOVER, CardNumber.EIGHT),
                Card(CardCategory.SPADE, CardNumber.ACE)
            )
        val result = cards.maxSumState()
        val expected = Cards.State.NoBurst(19)
        assertThat(result).isEqualTo(expected)
    }
}
