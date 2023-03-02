package blackjack.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class CardsTest {
    @Test
    fun `카드들을 생성할 수 있다`() {
        val cards = mutableListOf(Card(CardNumber.KING, CardShape.CLOVER), Card(CardNumber.TWO, CardShape.HEART))
        assertDoesNotThrow { Cards(cards) }
    }
}
