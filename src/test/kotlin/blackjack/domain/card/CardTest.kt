package blackjack.domain.card

import blackjack.fixture.JACK_SPADE
import blackjack.fixture.KING_SPADE
import blackjack.fixture.QUEEN_SPADE
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `카드가 Jack이면 10으로 계산한다`() {
        assertThat(JACK_SPADE.getNumber()).isEqualTo(10)
    }

    @Test
    fun `카드가 Queen이면 10으로 계산한다`() {
        assertThat(QUEEN_SPADE.getNumber()).isEqualTo(10)
    }

    @Test
    fun `카드가 King이면 10으로 계산한다`() {
        assertThat(KING_SPADE.getNumber()).isEqualTo(10)
    }
}
