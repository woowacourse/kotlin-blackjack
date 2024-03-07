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
            val dealer = Dealer()
            dealer.addCard(Card(CardNumber.`4`, Suit.`하트`))

            val player1 = buildPlayer("a", CardNumber.`3`)

            val statistics = GameStatistics(dealer, listOf(player1))
            val actual = statistics.dealerStatistics
            val expected = mapOf(GameResult.`승` to 1)
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `플레이어 2명이 졌을 때 딜러는 2번 승리한다`() {
            val dealer = Dealer()
            dealer.addCard(Card(CardNumber.`4`, Suit.`하트`))

            val player1 = buildPlayer("a", CardNumber.`3`)
            val player2 = buildPlayer("b", CardNumber.`3`)

            val statistics = GameStatistics(dealer, listOf(player1, player2))
            val actual = statistics.dealerStatistics
            val expected = mapOf(GameResult.`승` to 2)
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `플레이어 2명이 이겼을 때 딜러는 2번 패배한다`() {
            val dealer = Dealer()
            dealer.addCard(Card(CardNumber.`2`, Suit.`하트`))

            val player1 = buildPlayer("a", CardNumber.`3`)
            val player2 = buildPlayer("b", CardNumber.`3`)

            val statistics = GameStatistics(dealer, listOf(player1, player2))
            val actual = statistics.dealerStatistics
            val expected = mapOf(GameResult.`패` to 2)
            assertThat(actual).isEqualTo(expected)
        }


        @Test
        fun `플레이어 2명이 비겼을 때 딜러는 2번 비긴다`() {
            val dealer = Dealer()
            dealer.addCard(Card(CardNumber.`3`, Suit.`하트`))

            val player1 = buildPlayer("a", CardNumber.`3`)
            val player2 = buildPlayer("b", CardNumber.`3`)

            val statistics = GameStatistics(dealer, listOf(player1, player2))
            val actual = statistics.dealerStatistics
            val expected = mapOf(GameResult.`무` to 2)
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `플레이어 1명이 이기고 1명이 졌을 때 딜러는 1번 승리하고 1번 패배한다`() {
            val dealer = Dealer()
            dealer.addCard(Card(CardNumber.`3`, Suit.`하트`))

            val player1 = buildPlayer("a", CardNumber.`2`)
            val player2 = buildPlayer("b", CardNumber.`4`)

            val statistics = GameStatistics(dealer, listOf(player1, player2))
            val actual = statistics.dealerStatistics
            val expected = mapOf(GameResult.`승` to 1, GameResult.`패` to 1)
            assertThat(actual).isEqualTo(expected)
        }

    }

    @Test
    fun `딜러와 플레이어를 받아 플레이어들의 통계를 생성한다`() {
        val dealer = Dealer()
        dealer.addCard(Card(CardNumber.`4`, Suit.`하트`))

        val player1 = buildPlayer("a", CardNumber.`3`)

        val statistics = GameStatistics(dealer, listOf(player1))
        val actual = statistics.playerStatistics
        val expected = mapOf("a" to GameResult.`패`)
        assertThat(actual).isEqualTo(expected)
    }


    @Nested
    @DisplayName("승패 판정 테스트")
    inner class JudgeTest {

        @Test
        fun `플레이어만 bust될 때 무조건 패배한다`() {
            val dealer = Dealer()
            dealer.addCard(Card(CardNumber.`4`, Suit.`하트`))

            val player1 = buildPlayer("a", CardNumber.`J`, CardNumber.`Q`, CardNumber.`K`)

            val statistics = GameStatistics(dealer, listOf(player1))
            val actual = statistics.playerStatistics
            val expected = mapOf("a" to GameResult.`패`)
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `딜러와 플레이어가 둘 다 bust될 때 플레이어가 패배한다`() {
            val dealer = buildDealer(CardNumber.`J`, CardNumber.`Q`, CardNumber.`K`)

            val player1 = buildPlayer("a", CardNumber.`J`, CardNumber.`Q`, CardNumber.`K`)

            val statistics = GameStatistics(dealer, listOf(player1))
            val actual = statistics.playerStatistics
            val expected = mapOf("a" to GameResult.`패`)
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `둘 다 블랙잭인 경우 무승부로 한다`() {
            val dealer = buildDealer(CardNumber.`J`, CardNumber.`A`)

            val player1 = buildPlayer("a", CardNumber.`J`, CardNumber.`A`)

            val statistics = GameStatistics(dealer, listOf(player1))
            val actual = statistics.playerStatistics
            val expected = mapOf("a" to GameResult.`무`)
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `딜러가 블랙잭이고 플레이어가 21인 경우 패배한다`() {
            val dealer = buildDealer(CardNumber.`J`, CardNumber.`A`)

            val player1 = buildPlayer("a", CardNumber.`J`, CardNumber.`5`, CardNumber.`6`)

            val statistics = GameStatistics(dealer, listOf(player1))
            val actual = statistics.playerStatistics
            val expected = mapOf("a" to GameResult.`패`)
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `플레이어가 블랙잭이고 딜러가 21인 경우 승리한다`() {
            val dealer = buildDealer(CardNumber.`J`, CardNumber.`5`, CardNumber.`6`)

            val player1 = buildPlayer("a", CardNumber.`J`, CardNumber.`A`)

            val statistics = GameStatistics(dealer, listOf(player1))
            val actual = statistics.playerStatistics
            val expected = mapOf("a" to GameResult.`승`)
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `모두 블랙잭이 아니고 플레이어의 점수가 클 경우 플레이어가 승리한다`() {
            val dealer = buildDealer(CardNumber.`2`, CardNumber.`3`)

            val player1 = buildPlayer("a", CardNumber.`4`, CardNumber.`5`)

            val statistics = GameStatistics(dealer, listOf(player1))
            val actual = statistics.playerStatistics
            val expected = mapOf("a" to GameResult.`승`)
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `모두 블랙잭이 아니고 플레이어의 점수가 작을 경우 플레이어가 패배한다`() {
            val dealer = buildDealer(CardNumber.`4`, CardNumber.`5`)

            val player1 = buildPlayer("a", CardNumber.`2`, CardNumber.`3`)

            val statistics = GameStatistics(dealer, listOf(player1))
            val actual = statistics.playerStatistics
            val expected = mapOf("a" to GameResult.`패`)
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `모두 블랙잭이 아니고 점수가 같을 경우 무승부로 한다`() {
            val dealer = buildDealer(CardNumber.`2`, CardNumber.`3`)

            val player1 = buildPlayer("a", CardNumber.`2`, CardNumber.`3`)

            val statistics = GameStatistics(dealer, listOf(player1))
            val actual = statistics.playerStatistics
            val expected = mapOf("a" to GameResult.`무`)
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `터짐 -_-`() {
            val dealer = buildDealer(CardNumber.`6`, CardNumber.`10`, CardNumber.`9`)

            val player1 = buildPlayer("a", CardNumber.`5`, CardNumber.`7`, CardNumber.`6`)
            val player2 = buildPlayer("b", CardNumber.`A`, CardNumber.`3`, CardNumber.`8`)

            val statistics = GameStatistics(dealer, listOf(player1, player2))
            val actual = statistics.playerStatistics
            val expected = mapOf("a" to GameResult.`승`, "b" to GameResult.`승`)
            assertThat(actual).isEqualTo(expected)
        }
    }

    fun buildPlayer(name: String, vararg cardNumber: CardNumber): Player {
        val player = Player(name)
        val cardList = listOf(*cardNumber)
        cardList.forEach {
            player.addCard(Card(it, Suit.`하트`))
        }
        return player
    }

    fun buildDealer(vararg cardNumber: CardNumber): Dealer {
        val dealer = Dealer()
        val cardList = listOf(*cardNumber)
        cardList.forEach {
            dealer.addCard(Card(it, Suit.`하트`))
        }
        return dealer
    }
}
