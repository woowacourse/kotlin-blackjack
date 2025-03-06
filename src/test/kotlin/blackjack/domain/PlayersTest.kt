package blackjack.domain

import blackjack.model.CardDeck
import blackjack.model.Players
import blackjack.model.WinningResult.LOSE
import blackjack.model.WinningResult.PUSH
import blackjack.model.WinningResult.WIN
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayersTest {
    @Test
    fun `딜러의 점수보다 플레이어의 점수가 같으면 무승부를 반환한다`() {
        // given
        val playerName = "시아"
        val cardDeck = CardDeck()
        val players = Players(listOf(playerName), cardDeck)

        // when
        val dealerScore =
            players.players
                .first()
                .hand
                .score()

        // then
        assertThat(players.results(dealerScore)[playerName]).isEqualTo(PUSH)
    }

    @Test
    fun `딜러의 점수보다 플레이어의 점수가 높으면 우승을 반환한다`() {
        // given
        val playerName = "시아"
        val cardDeck = CardDeck()
        val players = Players(listOf(playerName), cardDeck)

        // when
        val dealerScore = -100

        // then
        assertThat(players.results(dealerScore)[playerName]).isEqualTo(WIN)
    }

    @Test
    fun `딜러의 점수보다 플레이어의 점수가 낮으면 패배를 반환한다`() {
        // given
        val playerName = "시아"
        val cardDeck = CardDeck()
        val players = Players(listOf(playerName), cardDeck)

        // when
        val dealerScore = 100

        // then
        assertThat(players.results(dealerScore)[playerName]).isEqualTo(LOSE)
    }
}
