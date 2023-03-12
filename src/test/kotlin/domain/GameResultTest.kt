package domain

import blackjack.domain.GameResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameResultTest {

    @Test
    fun `BLACKJACK의 반대는 LOSE`() {
        assertThat(!GameResult.BLACKJACK).isEqualTo(GameResult.LOSE)
    }

    @Test
    fun `WIN의 반대는 LOSE`() {
        assertThat(!GameResult.WIN).isEqualTo(GameResult.LOSE)
    }

    @Test
    fun `DRAW의 반대는 DRAW`() {
        assertThat(!GameResult.DRAW).isEqualTo(GameResult.DRAW)
    }

    @Test
    fun `LOSE의 반대는 WIN`() {
        assertThat(!GameResult.LOSE).isEqualTo(GameResult.WIN)
    }
}
