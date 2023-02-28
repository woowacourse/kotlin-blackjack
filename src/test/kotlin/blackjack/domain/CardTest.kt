package blackjack.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class CardTest {

    @Test
    fun `카드는 숫자를 생성할 수 있다`() {
        assertDoesNotThrow { Card(CardNumber.ONE) }
    }
}
