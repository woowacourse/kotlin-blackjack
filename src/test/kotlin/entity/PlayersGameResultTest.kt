package entity

import entity.card.Cards
import entity.result.BettingMoney
import entity.result.GameResultType
import entity.result.PlayersGameResult
import entity.users.Name
import entity.users.Player
import entity.users.UserInformation
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayersGameResultTest {
    @Test
    fun `플레이어의 게임 결과가 2승 1패이면 딜러의 게임 결과는 1승 2패이다`() {
        // given
        val player1 = Player(UserInformation(Name("test"), BettingMoney(0)), Cards(mutableListOf()))
        val player2 = Player(UserInformation(Name("test"), BettingMoney(0)), Cards(mutableListOf()))
        val player3 = Player(UserInformation(Name("test"), BettingMoney(0)), Cards(mutableListOf()))
        val playersGameResult = PlayersGameResult(mapOf(player1 to GameResultType.WIN, player2 to GameResultType.WIN, player3 to GameResultType.LOSE))

        // when
        val dealerResults = playersGameResult.makeDealerGameResult()

        // then
        assertThat(dealerResults.value).isEqualTo(mapOf(GameResultType.WIN to 1, GameResultType.LOSE to 2))
    }
}
