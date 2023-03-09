package entity

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayersGameResultTest {
    @Test
    fun `플레이어의 게임 결과가 2승 1패이면 딜러의 게임 결과는 1승 2패이다`() {
        // given
        val player1 = Player(Name("test"), Money(0), Cards())
        val player2 = Player(Name("test"), Money(0), Cards())
        val player3 = Player(Name("test"), Money(0), Cards())
        val playersGameResult = PlayersGameResult(
            mapOf(
                player1 to GameResult(GameResultType.WIN),
                player2 to GameResult(GameResultType.WIN),
                player3 to GameResult(GameResultType.LOSE)
            )
        )

        // when
        val dealerResults = playersGameResult.makeDealerGameResult()

        // then
        assertThat(dealerResults.value).isEqualTo(mapOf(GameResultType.WIN to 1, GameResultType.LOSE to 2))
    }

    fun GameResult(gameResultType: GameResultType): GameResult = GameResult(gameResultType, Money(0))
}
