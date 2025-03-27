package blackjack.model.state

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class StartGameTest {
    @Test
    fun `카드를 추가 하지 않으면 Stay을 반환한다`() {
        val startGame = StartGame()

        val actual = startGame.stop()

        Assertions.assertThat(actual).isInstanceOf(Stay::class.java)
    }
}
