package blackjack.domain

import blackjack.model.Players
import blackjack.model.ScoreCalculator
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PlayersTest {
    private val scoreCalculator = ScoreCalculator()

    @Test
    fun `플레이어가 0명일 때 오류를 반환한다`() {
        // given & when & then
        assertThrows<IllegalArgumentException> {
            Players.from(emptyList(), scoreCalculator)
        }
    }

    @Test
    fun `플레이어가 8명일 때 오류를 반환한다`() {
        // given
        val players = List(8) { "Player$it" }

        // when & then
        assertThrows<IllegalArgumentException> {
            Players.from(players, scoreCalculator)
        }
    }

    @Test
    fun `플레이어 이름이 중복되면 오류를 반환한다`() {
        // given
        val players = listOf("공백", "공백", "시아")

        // when & then
        assertThrows<IllegalArgumentException> {
            Players.from(players, scoreCalculator)
        }
    }
}
