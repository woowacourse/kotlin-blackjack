package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WinningStateTest {
    @Test
    fun `승을 뒤집을 수 있다`() {
        val actual = WinningState.WIN.reversed()
        val expect = WinningState.LOSS
        assertThat(actual).isEqualTo(expect)
    }
}
