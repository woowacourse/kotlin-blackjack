package blackjack.model.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameResultTest {
    @Test
    fun `타켓값이 임계값보다 작으면 Lose상태를 반환한다`() {
        // when
        val actual = GameResult.compare(1, 21)
        val expected = GameResult.Lose
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `타켓값이 임계값보다 크면 Win상태를 반환한다`() {
        // when
        val actual = GameResult.compare(21, 1)
        val expected = GameResult.Win
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `타켓값이 임계값과 같으면 Draw상태를 반환한다`() {
        // when
        val actual = GameResult.compare(21, 21)
        val expected = GameResult.Draw
        // then
        assertThat(actual).isEqualTo(expected)
    }
}
