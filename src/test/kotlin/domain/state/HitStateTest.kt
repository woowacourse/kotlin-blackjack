package domain.state

import domain.card.Card
import domain.card.CardCategory
import domain.card.CardNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import state.BustState
import state.HitState
import state.StayState

class HitStateTest {
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
