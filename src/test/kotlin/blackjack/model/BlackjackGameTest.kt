package blackjack.model

import blackjack.model.card.Card
import blackjack.model.card.Denomination
import blackjack.model.card.Suite
import blackjack.model.card.TestCardProvider
import blackjack.model.participant.Dealer
import blackjack.model.participant.PlayerName
import blackjack.model.participant.Players
import blackjack.model.result.GameResultType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackjackGameTest {
    @Test
    fun `게임 시작 시 딜러와 플레이어가 2장의 카드를 받는다`() {
        // given
        val dealer = Dealer()
        val players = Players.from(listOf("olive", "seogi"))

        // when
        BlackjackGame(dealer, players, TestCardProvider)

        // then
        val expected = listOf(Card(Denomination.TWO, Suite.HEART), Card(Denomination.TWO, Suite.HEART))
        assertThat(dealer.getCards()).isEqualTo(expected)
        players.playerGroup.forEach {
            assertThat(it.getCards()).isEqualTo(expected)
        }
    }

    @Test
    fun `게임의 최종 승패 결과를 계산한다`() {
        // given
        val dealer = Dealer()
        val players = Players.from(listOf("olive", "seogi", "chae"))

        dealer.receiveCard(Card(Denomination.EIGHT, Suite.HEART))

        players.playerGroup[0].receiveCard(Card("2"))
        players.playerGroup[1].receiveCard(Card("8"))
        players.playerGroup[2].receiveCard(Card("K"))

        // when
        val gameResultStorage = BlackjackGame(dealer, players, TestCardProvider).calculateGameResult()

        // then
        assertThat(gameResultStorage.dealerResult.results)
            .containsEntry(GameResultType.WIN, 1)
            .containsEntry(GameResultType.DRAW, 1)
            .containsEntry(GameResultType.LOSE, 1)
        assertThat(gameResultStorage.playersResult.results)
            .containsEntry(PlayerName("olive"), GameResultType.LOSE)
            .containsEntry(PlayerName("seogi"), GameResultType.DRAW)
            .containsEntry(PlayerName("chae"), GameResultType.WIN)
    }
}
