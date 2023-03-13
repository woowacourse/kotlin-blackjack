package domain

import domain.card.Card
import domain.card.CardCategory
import domain.card.CardNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {
    @Test
    fun `카드 번호의 총합을 계산한다`() {
        val cards =
            Cards(
                Card.of(CardCategory.CLOVER, CardNumber.TWO),
                Card.of(CardCategory.CLOVER, CardNumber.KING)
            )
        val actual = cards.score.value
        val expected = 12
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `카드를 1개 추가하면 사이즈가 1 증가한다`() {
        val cards =
            Cards(
                Card.of(CardCategory.CLOVER, CardNumber.TWO),
                Card.of(CardCategory.CLOVER, CardNumber.KING)
            )
        val card = Card.of(CardCategory.DIAMOND, CardNumber.FIVE)
        val newCards = cards.add(card)
        val actual = newCards.size
        val expected = 3
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `카드를 1개 추가하면 카드목록이 그 카드를 포함한다`() {
        val cards =
            Cards(
                Card.of(CardCategory.CLOVER, CardNumber.TWO),
                Card.of(CardCategory.CLOVER, CardNumber.KING)
            )
        val card = Card.of(CardCategory.DIAMOND, CardNumber.FIVE)
        val newCards = cards.add(card)
        val actual = newCards.size
        val expected = 3
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `에이스가 있다면 모두 1로 보고 더한 최소 합을 구한다`() {
        val cards =
            Cards(
                Card.of(CardCategory.CLOVER, CardNumber.TWO),
                Card.of(CardCategory.CLOVER, CardNumber.KING),
                Card.of(CardCategory.CLOVER, CardNumber.ACE)
            )
        val actual = cards.score.value
        val expected = 13
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `에이스가 있다면 에이스 한 개를 11로 보고 더한 최대 합과 상태를 구한다`() {
        val cards =
            Cards(
                Card.of(CardCategory.CLOVER, CardNumber.KING),
                Card.of(CardCategory.CLOVER, CardNumber.EIGHT),
                Card.of(CardCategory.SPADE, CardNumber.ACE)
            )
        val actual = cards.score.value
        val expected = 19
        assertThat(actual).isEqualTo(expected)
    }
}
