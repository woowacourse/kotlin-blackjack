package domain

import domain.card.Card
import domain.card.CardShape
import domain.card.CardValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RefereeTest {
    @Test
    fun `딜러는 18점, 유저1, 유저2의 점수는 19, 15점이다`() {
        // given
        val dealerScore = Score.valueOf(18)
        val users = listOf<User>(
            User(
                "유저1",
                Cards(
                    listOf(
                        Card(CardShape.DIAMONDS, CardValue.TEN),
                        Card(CardShape.DIAMONDS, CardValue.NINE)
                    )
                ),
                1000.0
            ),
            User(
                "유저2",
                Cards(
                    listOf(
                        Card(CardShape.DIAMONDS, CardValue.TEN),
                        Card(CardShape.DIAMONDS, CardValue.FIVE)
                    )
                ),
                1000.0
            )
        )

        // when
        val actual = Referee(dealerScore).getResult(users)
        val expected = listOf<GameResult>(GameResult.WIN, GameResult.LOSE)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `딜러는 22점, 유저1, 유저2의 점수는 19, 15점이다`() {
        // given
        val dealerScore = Score.valueOf(22)
        val users = listOf<User>(
            User(
                "유저1",
                Cards(
                    listOf(
                        Card(CardShape.DIAMONDS, CardValue.TEN),
                        Card(CardShape.DIAMONDS, CardValue.NINE)
                    )
                ),
                1000.0
            ),
            User(
                "유저2",
                Cards(
                    listOf(
                        Card(CardShape.DIAMONDS, CardValue.TEN),
                        Card(CardShape.DIAMONDS, CardValue.FIVE)
                    )
                ),
                1000.0
            )
        )

        // when
        val actual = Referee(dealerScore).getResult(users)
        val expected = listOf<GameResult>(GameResult.WIN, GameResult.WIN)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `딜러는 22점, 유저1, 유저2의 점수는 23, 15점이다`() {
        // given
        val dealerScore = Score.valueOf(22)
        val users = listOf<User>(
            User(
                "유저1",
                Cards(
                    listOf(
                        Card(CardShape.DIAMONDS, CardValue.TEN),
                        Card(CardShape.DIAMONDS, CardValue.TEN),
                        Card(CardShape.DIAMONDS, CardValue.THREE),
                    )
                ),
                1000.0
            ),
            User(
                "유저2",
                Cards(
                    listOf(
                        Card(CardShape.DIAMONDS, CardValue.TEN),
                        Card(CardShape.DIAMONDS, CardValue.FIVE)
                    )
                ),
                1000.0
            )
        )

        // when
        val actual = Referee(dealerScore).getResult(users)
        val expected = listOf<GameResult>(GameResult.LOSE, GameResult.WIN)

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
