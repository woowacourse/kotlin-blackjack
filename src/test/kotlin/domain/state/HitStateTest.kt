package domain.state

import domain.card.Card
import domain.card.CardCategory
import domain.card.CardNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import state.BustState
import state.HitState
import state.StayState

class HitStateTest {
    @Test
    fun `Hit상태가 아니라면 Hit객체를 생성할 수 없다`() {
        assertAll(
            "hit상태가 아니라면 예외가 발생한다.",
            {
                // 합이 21 미만이지만 카드가 한장인 경우
                assertThrows<IllegalStateException> {
                    HitState(
                        Card.of(CardCategory.CLOVER, CardNumber.EIGHT)
                    )
                }
            },
            {
                // 합이 21이지만 카드가 두 장이라서 블랙잭 상태인 경우
                assertThrows<IllegalStateException> {
                    HitState(
                        Card.of(CardCategory.CLOVER, CardNumber.JACK),
                        Card.of(CardCategory.CLOVER, CardNumber.ACE)
                    )
                }
            },
            {
                // 카드가 두 장보다 많지만 21을 넘어서 버스트 상태인 경우
                assertThrows<IllegalStateException> {
                    HitState(
                        Card.of(CardCategory.CLOVER, CardNumber.EIGHT),
                        Card.of(CardCategory.CLOVER, CardNumber.EIGHT),
                        Card.of(CardCategory.CLOVER, CardNumber.SIX)
                    )
                }
            },
        )
    }

    @Test
    fun `카드를 뽑는데 21을 넘지 않으면 HitState가 된다`() {
        val hitState = HitState(
            Card.of(CardCategory.CLOVER, CardNumber.ACE),
            Card.of(CardCategory.CLOVER, CardNumber.EIGHT)
        )
        val result = hitState.draw(Card.of(CardCategory.CLOVER, CardNumber.FIVE))
        assertThat(result).isInstanceOf(HitState::class.java)
    }

    @Test
    fun `카드를 뽑는데 21이 넘으면 BustState가 된다`() {
        val hitState = HitState(
            Card.of(CardCategory.CLOVER, CardNumber.ACE),
            Card.of(CardCategory.CLOVER, CardNumber.EIGHT)
        )
        val result = hitState.draw(Card.of(CardCategory.CLOVER, CardNumber.FIVE))
            .draw(Card.of(CardCategory.CLOVER, CardNumber.EIGHT))
        assertThat(result).isInstanceOf(BustState::class.java)
    }

    @Test
    fun `카드를 그만 뽑고 싶다면 StayState가 된다`() {
        val hitState = HitState(
            Card.of(CardCategory.CLOVER, CardNumber.ACE),
            Card.of(CardCategory.CLOVER, CardNumber.EIGHT)
        )
        val result = hitState.stay()
        assertThat(result).isInstanceOf(StayState::class.java)
    }
}
