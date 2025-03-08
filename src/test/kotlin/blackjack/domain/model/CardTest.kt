package blackjack.domain.model

import blackjack.domain.model.card.Card
import blackjack.domain.model.card.CardNumber
import blackjack.domain.model.card.Suit
import org.junit.jupiter.api.assertDoesNotThrow
import kotlin.test.Test

class CardTest {
    @Test
    fun `유효한 카드 값을 받아 카드를 생성한다`() {
        // given
        val cardNumber = CardNumber.ACE
        val suit = Suit.SPADE

        // when then
        assertDoesNotThrow { Card(cardNumber, suit) }
    }
}
