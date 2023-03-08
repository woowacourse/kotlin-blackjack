package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class GameResultTest {
    fun Player(name: String, vararg cards: Int): Player {
        return Player(
            Name(name),
            Cards(
                cards.map { number ->
                    Card.of(
                        CardCategory.CLOVER,
                        CardNumber.values().find { it.value == number.toInt() } ?: CardNumber.FIVE,
                    )
                },
            ),
        )
    }

    fun Dealer(vararg cards: Int): Dealer {
        return Dealer(
            Cards(
                cards.map { number ->
                    Card.of(
                        CardCategory.CLOVER,
                        CardNumber.values().find { it.value == number.toInt() } ?: CardNumber.FIVE,
                    )
                },
            ),
        )
    }

    fun Players(vararg player: Player): Players {
        return Players(player.toList())
    }

    @Test
    fun `플레이어들의 승패 정보를 반환한다`() {
        val player1 = Player(
            "pobi",
            8,
            9,
        )
        val player2 = Player(
            "jason",
            7,
            9,
        )
        val participants = Participants(
            players = Players(
                player1,
                player2,
            ),
            dealer = Dealer(8, 9),
        )
        val gameResult = GameResult(participants)
        val actual = gameResult.getPlayersGameResult2()
        assertAll(
            { assertThat(actual[player1]).isEqualTo(GameResultType.DRAW) },
            { assertThat(actual[player2]).isEqualTo(GameResultType.LOSE) },
        )
    }

    @Test
    fun `딜러의 승패 정보를 반환한다`() {
        val participants = Participants(
            players = Players(
                Player(
                    "pobi",
                    8,
                    9,
                ),
                Player(
                    "jason",
                    8,
                    9,
                ),
            ),
            dealer = Dealer(8, 9),
        )
        val gameResult = GameResult(participants)
        val actual = gameResult.getDealerGameResult()
        assertAll(
            { assertThat(actual[GameResultType.WIN]).isEqualTo(0) },
            { assertThat(actual[GameResultType.DRAW]).isEqualTo(2) },
            { assertThat(actual[GameResultType.LOSE]).isEqualTo(0) },
        )
    }

    @Test
    fun `플레이어가 그냥 이겼을 때 플레이어가 건 돈의 1배를 수익으로 반환한다`() {
        // given
        val player1 = Player(
            "pobi",
            8,
            9,
        )
        val player2 = Player(
            "jason",
            7,
            9,
        )
        val participants = Participants(
            players = Players(
                player1,
                player2,
            ),
            dealer = Dealer(8, 9),
        )
        val gameResult = GameResult(participants)
        // when
        // then
    }

    @Test
    fun `플레이어가 블랙잭으로 이겼을 때 플레이어가 건 돈의 2분의3 배를 수익으로 반환한다`() {
    }

    @Test
    fun `플레이어가 졌을때 플레이어가 건 돈의 -1 배를 수익으로 반환한다`() {
    }

    @Test
    fun `플레이어가 무승부일때 0을 수익으로 반환한다`() {
    }
}
