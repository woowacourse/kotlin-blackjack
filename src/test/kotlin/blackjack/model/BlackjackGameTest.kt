package blackjack.model

import blackjack.model.card.Card
import blackjack.model.card.TestCardProvider
import blackjack.model.participant.Dealer
import blackjack.model.participant.Player
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
        val expected = listOf(Card.of("2", "하트"), Card.of("2", "하트"))
        assertThat(dealer.getCards()).isEqualTo(expected)
        players.playerGroup.forEach {
            assertThat(it.getCards()).isEqualTo(expected)
        }
    }

    @Test
    fun `게임의 최종 승패 결과를 계산한다`() {
        // given
        val dealer = Dealer()
        val players =
            Players(
                listOf(
                    Player.createPlayerWithCard("olive", listOf(Card.of("2", "하트"))),
                    Player.createPlayerWithCard("chae", listOf(Card.of("8", "하트"))),
                    Player.createPlayerWithCard("seogi", listOf(Card.of("K", "하트"))),
                ),
            )
        dealer.receiveCard(Card.of("8", "하트"))

        // when
        val gameResultStorage = BlackjackGame(dealer, players, TestCardProvider).calculateGameResult()

        // then
        assertThat(gameResultStorage.dealerResult.results)
            .containsEntry(GameResultType.WIN, 1)
            .containsEntry(GameResultType.DRAW, 1)
            .containsEntry(GameResultType.LOSE, 1)

        assertThat(gameResultStorage.playersResult.results)
            .containsEntry(PlayerName("olive"), GameResultType.LOSE)
            .containsEntry(PlayerName("chae"), GameResultType.DRAW)
            .containsEntry(PlayerName("seogi"), GameResultType.WIN)
    }
}
