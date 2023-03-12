package domain.state

import domain.card.Card
import domain.card.CardCategory
import domain.card.CardNumber
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import state.BustState

class BustStateTest {
    @Test
    fun `카드가 버스트가 아닌 경우 BustState를 생성할 수 없다`() {
        assertThrows<IllegalStateException> {
            BustState(
                Card.of(CardCategory.CLOVER, CardNumber.JACK),
                Card.of(CardCategory.DIAMOND, CardNumber.ACE),
                Card.of(CardCategory.CLOVER, CardNumber.JACK)
            )
        }
    }
}
