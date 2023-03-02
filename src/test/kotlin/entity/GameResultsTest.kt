package entity

import model.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameResultsTest {
    @Test
    fun `플레이어의 게임 결과가 2승 1패이면 딜러의 게임 결과는 1승 2패이다`() {
        // given
        val user1 = User(Cards(mutableListOf()))
        val user2 = User(Cards(mutableListOf()))
        val user3 = User(Cards(mutableListOf()))
        val gameResults = GameResults(mapOf(user1 to GameResultType.WIN, user2 to GameResultType.WIN, user3 to GameResultType.LOSE))

        // when
        val dealerResults = gameResults.makeDealerGameResult()

        // then
        assertThat(dealerResults).isEqualTo(mapOf(GameResultType.WIN to 1, GameResultType.LOSE to 2))
    }
}
