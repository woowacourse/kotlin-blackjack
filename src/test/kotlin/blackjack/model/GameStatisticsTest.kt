package blackjack.model

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

    @Nested
    @DisplayName("승패 판정 테스트")
    inner class JudgeTest {
        @Test
        fun `플레이어만 bust될 때 무조건 패배한다`() {
            val dealer = buildDealer(four)

            val player = buildPlayer("a", ten, ten, ten)

            val actual = player versus dealer
            val expected = GameResult.Lose
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `딜러와 플레이어가 둘 다 bust될 때 플레이어가 패배한다`() {
            val dealer = buildDealer(ten, ten, ten)

            val player = buildPlayer("a", ten, ten, ten)

            val actual = player versus dealer
            val expected = GameResult.Lose
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `둘 다 블랙잭인 경우 무승부로 한다`() {
            val dealer = buildDealer(ten, ace)

            val player = buildPlayer("a", ten, ace)

            val actual = player versus dealer
            val expected = GameResult.Draw
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `딜러가 블랙잭이고 플레이어가 21인 경우 패배한다`() {
            val dealer = buildDealer(ten, ace)

            val player = buildPlayer("a", ten, five, six)

            val actual = player versus dealer
            val expected = GameResult.Lose
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `플레이어가 블랙잭이고 딜러가 21인 경우 승리한다`() {
            val dealer = buildDealer(ten, five, six)

            val player = buildPlayer("a", ten, ace)

            val actual = player versus dealer
            val expected = GameResult.Win
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `모두 블랙잭이 아니고 플레이어의 점수가 클 경우 플레이어가 승리한다`() {
            val dealer = buildDealer(two, three)

            val player = buildPlayer("a", four, five)

            val actual = player versus dealer
            val expected = GameResult.Win
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `모두 블랙잭이 아니고 플레이어의 점수가 작을 경우 플레이어가 패배한다`() {
            val dealer = buildDealer(four, five)

            val player = buildPlayer("a", two, three)

            val actual = player versus dealer
            val expected = GameResult.Lose
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `모두 블랙잭이 아니고 점수가 같을 경우 무승부로 한다`() {
            val dealer = buildDealer(two, three)

            val player = buildPlayer("a", two, three)

            val actual = player versus dealer
            val expected = GameResult.Draw
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `딜러가 버스트 된 경우 플레이어가 승리한다`() {
            val dealer = buildDealer(six, ten, nine)

            val player = buildPlayer("a", five, seven, six)

            val actual = player versus dealer
            val expected = GameResult.Win
            assertThat(actual).isEqualTo(expected)
        }
    }
}
