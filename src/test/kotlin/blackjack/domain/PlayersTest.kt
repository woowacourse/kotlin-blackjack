package blackjack.domain

import blackjack.model.Players
import blackjack.model.ScoreCalculator
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PlayersTest {
    private val scoreCalculator = ScoreCalculator()

    @Test
    fun `플레이어 인원 수는 0명 초과이다`() {
        // given & when & then
        assertThrows<IllegalArgumentException> {
            Players.from(emptyList(), scoreCalculator)
        }
    }

    @Test
    fun `플레이어 인원 수는 8명 미만이다`() {
        // given
        val players = List(8) { "Player$it" }

        // when & then
        assertThrows<IllegalArgumentException> {
            Players.from(players, scoreCalculator)
        }
    }

    @Test
    fun `플레이어 이름은 중복될 수 없다`() {
        // given
        val players = listOf("공백", "공백", "시아")

        // when & then
        assertThrows<IllegalArgumentException> {
            Players.from(players, scoreCalculator)
        }
    }
}
