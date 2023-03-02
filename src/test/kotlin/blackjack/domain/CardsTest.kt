package blackjack.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class CardsTest {
    @Test
    fun `카드들을 생성할 수 있다`() {
        val cards = mutableListOf(Card(CardNumber.KING, CardShape.CLOVER), Card(CardNumber.TWO, CardShape.HEART))
        assertDoesNotThrow { Cards(cards) }
    }

    @Test
    fun `처음으로 받은 카드가 두장인지 확인한다`() {
        val cards = mutableListOf(Card(CardNumber.FOUR, CardShape.DIAMOND), Card(CardNumber.JACK, CardShape.CLOVER))
        assertDoesNotThrow { Cards(cards) }
    }

    @Test
    fun `처음으로 받은 카드가 두장이 아니라면 에러가 발생한다`() {
        val cards = mutableListOf(
            Card(CardNumber.FOUR, CardShape.DIAMOND),
            Card(CardNumber.JACK, CardShape.CLOVER),
            Card(CardNumber.KING, CardShape.HEART)
        )
        assertThrows<IllegalArgumentException> { Cards(cards) }
    }
}
