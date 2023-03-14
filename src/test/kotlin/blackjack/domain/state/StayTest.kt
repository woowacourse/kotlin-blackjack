package blackjack.domain.state

import blackjack.domain.card.Cards
import blackjack.domain.state.Fixtures.CLOVER_ACE
import blackjack.domain.state.Fixtures.CLOVER_KING
import blackjack.domain.state.Fixtures.CLOVER_NINE
import blackjack.domain.state.Fixtures.CLOVER_TWO
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class StayTest {
    @Test
    fun `스테이는 더이상 카드를 뽑을 수 없다`() {
        assertThrows<IllegalStateException> { Stay(Cards(CLOVER_KING, CLOVER_TWO)).draw(CLOVER_KING) }
    }

    @Test
    fun `스테이는 점수는 20점이하다`() {
        val state: State = Stay(Cards(CLOVER_KING, CLOVER_NINE, CLOVER_ACE))
        Assertions.assertThat(state.score.value).isEqualTo(20)
    }

    @Test
    fun `스테이는 점수는 21점 이상이면 에러가 발생한다`() {
        assertThrows<IllegalArgumentException> { Stay(Cards(CLOVER_KING, CLOVER_NINE, CLOVER_TWO)) }
    }
}
