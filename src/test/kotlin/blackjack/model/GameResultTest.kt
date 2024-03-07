package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class GameResultTest {
    @ParameterizedTest
    @CsvSource(value = ["1/0/1", "0/2/0", "2/2/2"], delimiter = '/')
    fun `승리, 무승부, 패배의 정보를 갖는다`(
        win: Int,
        push: Int,
        defeat: Int,
    ) {
        val gameResult = GameResult(win = win, push = push, defeat = defeat)

        assertThat(gameResult.win).isEqualTo(win)
        assertThat(gameResult.push).isEqualTo(push)
        assertThat(gameResult.defeat).isEqualTo(defeat)
    }
}
