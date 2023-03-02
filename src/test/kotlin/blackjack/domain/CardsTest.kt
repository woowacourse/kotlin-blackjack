package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class CardsTest {
    @Test
    fun `카드들을 생성할 수 있다`() {
        val cards = listOf(Card(CardNumber.KING, CardShape.CLOVER), Card(CardNumber.TWO, CardShape.HEART))
        assertDoesNotThrow { Cards(cards) }
    }

    @Test
    fun `처음으로 받은 카드가 두장인지 확인한다`() {
        val cards = listOf(Card(CardNumber.FOUR, CardShape.DIAMOND), Card(CardNumber.JACK, CardShape.CLOVER))
        assertDoesNotThrow { Cards(cards) }
    }

    @Test
    fun `처음으로 받은 카드가 두장이 아니라면 에러가 발생한다`() {
        val cards = listOf(
            Card(CardNumber.FOUR, CardShape.DIAMOND),
            Card(CardNumber.JACK, CardShape.CLOVER),
            Card(CardNumber.KING, CardShape.HEART)
        )
        assertThrows<IllegalArgumentException> { Cards(cards) }
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
}
