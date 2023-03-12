package domain.state

import domain.card.Card
import domain.card.CardCategory
import domain.card.CardNumber
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import state.StayState

class StayTest {
    @Test
    fun `블랙잭이거나 버스트이면 Stay상태가 될 수 없다`() {
        assertAll(
            "카드가 두 장 미만이고 블랙잭이거나 버스트이면 예외가 발생한다.",
            {
                // 카드가 한장인 경우
                assertThrows<IllegalStateException> {
                    StayState(
                        Card.of(CardCategory.CLOVER, CardNumber.EIGHT)
                    )
                }
            },
            {
                // 카드가 두 장이상이지만 블랙잭 상태인 경우
                assertThrows<IllegalStateException> {
                    StayState(
                        Card.of(CardCategory.CLOVER, CardNumber.JACK),
                        Card.of(CardCategory.CLOVER, CardNumber.ACE)
                    )
                }
            },
            {
                // 카드가 두 장이상이지만 버스트 상태인 경우
                assertThrows<IllegalStateException> {
                    StayState(
                        Card.of(CardCategory.CLOVER, CardNumber.EIGHT),
                        Card.of(CardCategory.CLOVER, CardNumber.EIGHT),
                        Card.of(CardCategory.CLOVER, CardNumber.SIX)
                    )
                }
            },
        )
    }
}
