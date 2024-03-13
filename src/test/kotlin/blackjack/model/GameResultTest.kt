package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class GameResultTest {
    @ParameterizedTest
    @CsvSource(value = ["1/1", "0/1", "2/2"], delimiter = '/')
    fun `승리, 패배의 정보를 갖는다`(
        win: Int,
        lose: Int,
    ) {
        println(win)
        println(lose)
        val gameResult = GameResult(win = win, lose = lose)

        assertThat(gameResult.win).isEqualTo(win)
        assertThat(gameResult.lose).isEqualTo(lose)
    }
}
