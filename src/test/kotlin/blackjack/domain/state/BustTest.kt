package blackjack.domain.state

import blackjack.domain.card.Cards
import blackjack.domain.state.Fixtures.CLOVER_ACE
import blackjack.domain.state.Fixtures.CLOVER_JACK
import blackjack.domain.state.Fixtures.CLOVER_KING
import blackjack.domain.state.Fixtures.CLOVER_QUEEN
import blackjack.domain.state.Fixtures.CLOVER_TWO
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BustTest {
    @Test
    fun `버스트의 더이상 카드를 뽑을 수 없다`() {
        assertThrows<IllegalStateException> { Bust(Cards(CLOVER_KING, CLOVER_QUEEN, CLOVER_JACK)).draw(CLOVER_KING) }
    }

    @Test
    fun `버스트의 점수는 22점이상이다`() {
        val state: State = Bust(Cards(CLOVER_KING, CLOVER_QUEEN, CLOVER_TWO))
        assertThat(state.score.value).isEqualTo(22)
    }

    @Test
    fun `버스트의 점수가 21이 아니면 에러가 발생한다`() {
        assertThrows<IllegalArgumentException> { Bust(Cards(CLOVER_KING, CLOVER_QUEEN, CLOVER_ACE)) }
    }
}
