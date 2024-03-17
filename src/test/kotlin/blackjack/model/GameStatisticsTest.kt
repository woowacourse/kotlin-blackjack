package blackjack.model

import blackjack.model.statistics.DealerStatistics
import blackjack.model.statistics.PlayerStatistic
import blackjack.model.statistics.PlayerStatistics
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class GameStatisticsTest {
    @Nested
    @DisplayName("dealerStatistics가 적절한 값을 갖는지 테스트")
    inner class DealerStatisticsTest {
        @Test
        fun `플레이어 1명이 졌을 때 딜러는 1번 승리한다`() {
            val dealer = buildDealer(four)

            val player1 = buildPlayer("a", three)

            val actual = DealerStatistics(dealer, listOf(player1)).getWinCount()
            val expected = 1
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `플레이어 2명이 졌을 때 딜러는 2번 승리한다`() {
            val dealer = Dealer()
            dealer.addCard(Card(CardNumber.Four, Suit.Heart))

            val player1 = buildPlayer("a", three)
            val player2 = buildPlayer("b", three)

            val actual = DealerStatistics(dealer, listOf(player1, player2)).getWinCount()
            val expected = 2
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `플레이어 2명이 이겼을 때 딜러는 2번 패배한다`() {
            val dealer = buildDealer(two)

            val player1 = buildPlayer("a", three)
            val player2 = buildPlayer("b", three)

            val actual = DealerStatistics(dealer, listOf(player1, player2)).getLoseCount()
            val expected = 2
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `플레이어 2명이 비겼을 때 딜러는 2번 비긴다`() {
            val dealer = buildDealer(three)

            val player1 = buildPlayer("a", three)
            val player2 = buildPlayer("b", three)

            val actual = DealerStatistics(dealer, listOf(player1, player2)).getDrawCount()
            val expected = 2
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `플레이어 1명이 이기고 1명이 졌을 때 딜러는 1번 승리하고 1번 패배한다`() {
            val dealer = buildDealer(three)

            val player1 = buildPlayer("a", two)
            val player2 = buildPlayer("b", four)
            val dealerStatistics = DealerStatistics(dealer, listOf(player1, player2))

            val actualA = dealerStatistics.getWinCount()
            val expectedA = 1
            assertThat(actualA).isEqualTo(expectedA)

            val actualB = dealerStatistics.getLoseCount()
            val expectedB = 1
            assertThat(actualB).isEqualTo(expectedB)
        }
    }

    @Test
    fun `딜러와 플레이어를 받아 플레이어들의 통계를 생성한다`() {
        val dealer = buildDealer(four)

        val player1 = buildPlayer("a", three)

        val actual = PlayerStatistics(dealer, listOf(player1)).iterator().next()
        val expected = PlayerStatistic(player1, GameResult.Lose)
        assertThat(actual).isEqualTo(expected)
    }

}
