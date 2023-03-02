package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class CardTest {

    @Test
    fun `카드는 숫자를 생성할 수 있다`() {
        assertDoesNotThrow { Card(CardNumber.ONE, CardShape.CLOVER) }
    }

    @Test
    fun `카드는 모양을 생성할 수 있다`() {
        assertDoesNotThrow { Card(CardNumber.TWO, CardShape.HEART) }
    }
}
