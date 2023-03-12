package blackjack.domain.state

import blackjack.domain.CardBunchForState
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HitTest {
    @Test
    fun `카드를 뽑았을때 21이 넘는다면 Burst객체를 반환한다`() {
        val hit = Hit(CardBunchForState(Cards.nine, Cards.eight))
        assertThat(hit.draw(Cards.jack)).isInstanceOf(Burst::class.java)
    }
}
