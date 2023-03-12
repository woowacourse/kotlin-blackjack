package blackjack.domain.state

import blackjack.domain.CardBunch
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerHitTest {
    @Test
    fun `카드를 뽑았을때 21이 넘는다면 Burst객체를 반환한다`() {
        val hit = DealerHit(CardBunch(Cards.nine, Cards.eight))
        assertThat(hit.draw(Cards.jack)).isInstanceOf(Burst::class.java)
    }

    @Test
    fun `카드를 뽑았을때 16이 넘지 않는다면 DealerHit객체를 반환한다`() {
        val hit = DealerHit(CardBunch(Cards.two, Cards.eight))
        assertThat(hit.draw(Cards.three)).isInstanceOf(DealerHit::class.java)
    }

    @Test
    fun `카드를 뽑았을때 16이 넘고 21 이하라면 Stay객체를 반환한다`() {
        val hit = DealerHit(CardBunch(Cards.two, Cards.eight))
        assertThat(hit.draw(Cards.nine)).isInstanceOf(Stay::class.java)
    }

    @Test
    fun `카드를 뽑았을때 16이 넘지 않는다면 DelaerHit객체를 반환한다(카드 2번 뽑음)`() {
        val hit = DealerHit(CardBunch(Cards.two, Cards.eight))
        assertThat(hit.draw(Cards.two).draw(Cards.three)).isInstanceOf(DealerHit::class.java)
    }

    @Test
    fun `카드를 뽑았을때 21이 넘는다면 Burst객체를 반환한다(카드 2번 뽑음)`() {
        val hit = DealerHit(CardBunch(Cards.two, Cards.three))
        assertThat(hit.draw(Cards.king).draw(Cards.jack)).isInstanceOf(Burst::class.java)
    }
}
