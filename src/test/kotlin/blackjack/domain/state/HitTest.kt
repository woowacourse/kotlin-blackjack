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

    @Test
    fun `카드를 뽑았을때 21이 넘지 않는다면 Hit객체를 반환한다`() {
        val hit = Hit(CardBunchForState(Cards.two, Cards.eight))
        assertThat(hit.draw(Cards.three)).isInstanceOf(Hit::class.java)
    }

    @Test
    fun `stay함수를 호출했을때 Stay객체를 반환한다`() {
        val hit = Hit(CardBunchForState(Cards.two, Cards.eight))
        assertThat(hit.stay()).isInstanceOf(Stay::class.java)
    }
}
