package blackjack.model.participant.state

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HitTest {
    @Test
    fun `사용자가 그 다음 턴에서 Hit을 원할 때, Hit을 반환한다`() {
        val actual = Hit().nextTurn(10, true)
        assertThat(actual is Hit)
    }

    @Test
    fun `사용자가 그 다음 턴에서 Hit를 원할 때, 카드 점수가 21 이상이면 Bust를 반환한다`() {
        val actual = Hit().nextTurn(21, true)
        assertThat(actual is Bust2).isTrue
    }

    @Test
    fun `사용자가 그 다음 턴에서 카드를 받지 않을 때, Stay를 반환한다`() {
        val actual = Hit().nextTurn(10, false)
        assertThat(actual is Stay).isTrue
    }
}
