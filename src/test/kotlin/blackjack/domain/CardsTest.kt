package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class CardsTest {
    @Test
    fun `카드들을 생성할 수 있다`() {
        val cards = listOf(Card(CardNumber.KING, CardShape.CLOVER), Card(CardNumber.TWO, CardShape.HEART))
        assertDoesNotThrow { Cards(cards) }
    }

    @Test
    fun `새로운 카드를 받아 가진 카드에 추가한다`() {
        val cards = Cards(
            listOf(
                Card(CardNumber.FOUR, CardShape.DIAMOND), Card(CardNumber.JACK, CardShape.CLOVER)
            )
        )
        cards.addCard(Card(CardNumber.KING, CardShape.HEART))
        assertThat(cards.values.size).isEqualTo(3)
    }

    @Test
    fun `갖고 있는 카드 숫자의 합을 계산해 반환한다`() {
        val cards = Cards(
            listOf(
                Card(CardNumber.FOUR, CardShape.DIAMOND),
                Card(CardNumber.JACK, CardShape.CLOVER)
            )
        )
        val actual = cards.sumCardsNumber()
        assertThat(actual).isEqualTo(14)
    }
}
