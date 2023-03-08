package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import view.GameResult

class RefereeTest {
    @Test
    fun `딜러는 18점, 유저1, 유저2의 점수는 19, 15점이다`() {
        // given
        val dealerScore = 18
        val userScore = listOf(19, 15)

        // when
        val actual = Referee(dealerScore, userScore).getResult()
        val expected = listOf<GameResult>(GameResult.WIN, GameResult.LOSE)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `딜러는 22점, 유저1, 유저2의 점수는 19, 15점이다`() {
        // given
        val dealerScore = 22
        val userScore = listOf(19, 15)

        // when
        val actual = Referee(dealerScore, userScore).getResult()
        val expected = listOf<GameResult>(GameResult.WIN, GameResult.WIN)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `딜러는 22점, 유저1, 유저2의 점수는 119, 15점이다`() {
        // given
        val dealerScore = 22
        val userScore = listOf(119, 15)

        // when
        val actual = Referee(dealerScore, userScore).getResult()
        val expected = listOf<GameResult>(GameResult.DRAW, GameResult.WIN)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `딜러, 유저1, 유저2가 블랙잭일 경우, 딜러가 승리한다`() {
        // given
        val dealerScore = 21
        val userScore = listOf(21, 21)

        // when
        val actual = Referee(dealerScore, userScore).getResult()
        val expected = listOf<GameResult>(GameResult.LOSE, GameResult.LOSE)

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
