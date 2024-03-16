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
        fun `10000원을 배팅한 플레이어 1명이 이기면 딜러는 10000원을 잃는다`() {
            val dealer = buildDealer(CardNumber.THREE)
            val player1 = buildPlayer(CardNumber.FOUR)

            val statistics = GameStatistics(dealer, listOf(player1))
            val actual = statistics.dealerStatistics
            val expected = -10000L
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `10000원을 배팅한 플레이어 1명이 지면 딜러는 10000원을 얻는다`() {
            val dealer = buildDealer(CardNumber.FOUR)
            val player1 = buildPlayer(CardNumber.THREE)

            val statistics = GameStatistics(dealer, listOf(player1))
            val actual = statistics.dealerStatistics
            val expected = 10000L
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `10000원을 배팅한 플레이어 1명이 비기면 딜러는 0원을 얻는다`() {
            val dealer = buildDealer(CardNumber.FOUR)
            val player1 = buildPlayer(CardNumber.FOUR)

            val statistics = GameStatistics(dealer, listOf(player1))
            val actual = statistics.dealerStatistics
            val expected = 0L
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `10000원을 배팅한 플레이어 1명이 블랙잭이면 딜러는 15000원을 잃는다`() {
            val dealer = buildDealer(CardNumber.FOUR)
            val player1 = buildPlayer(CardNumber.TEN, CardNumber.ACE)

            val statistics = GameStatistics(dealer, listOf(player1))
            val actual = statistics.dealerStatistics
            val expected = -15000L
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `10000원을 배팅한 플레이어 2명이 지면 딜러는 20000원을 얻는다`() {
            val dealer = buildDealer(CardNumber.FOUR)

            val player1 = buildPlayer(CardNumber.THREE)
            val player2 = buildPlayer2(CardNumber.THREE)

            val statistics = GameStatistics(dealer, listOf(player1, player2))
            val actual = statistics.dealerStatistics
            val expected = 20000L
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `10000원을 배팅한 플레이어 2명이 이기면 딜러는 20000원을 잃는다`() {
            val dealer = buildDealer(CardNumber.TWO)

            val player1 = buildPlayer(CardNumber.THREE)
            val player2 = buildPlayer2(CardNumber.THREE)

            val statistics = GameStatistics(dealer, listOf(player1, player2))
            val actual = statistics.dealerStatistics
            val expected = -20000L
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `플레이어 2명이 비겼을 때 딜러는 0원을 얻는다`() {
            val dealer = buildDealer(CardNumber.THREE)

            val player1 = buildPlayer(CardNumber.THREE)
            val player2 = buildPlayer2(CardNumber.THREE)

            val statistics = GameStatistics(dealer, listOf(player1, player2))
            val actual = statistics.dealerStatistics
            val expected = 0L
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `플레이어 1명이 이기고 1명이 졌을 때 딜러는 0원을 얻는다`() {
            val dealer = buildDealer(CardNumber.THREE)

            val player1 = buildPlayer(CardNumber.TWO)
            val player2 = buildPlayer2(CardNumber.FOUR)

            val statistics = GameStatistics(dealer, listOf(player1, player2))
            val actual = statistics.dealerStatistics
            val expected = 0L
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `10000원을 배팅한 플레이어 2명이 블랙잭이면 딜러는 30000원을 잃는다`() {
            val dealer = buildDealer(CardNumber.TWO)

            val player1 = buildPlayer(CardNumber.TEN, CardNumber.ACE)
            val player2 = buildPlayer2(CardNumber.TEN, CardNumber.ACE)

            val statistics = GameStatistics(dealer, listOf(player1, player2))
            val actual = statistics.dealerStatistics
            val expected = -30000L
            assertThat(actual).isEqualTo(expected)
        }
    }

    @Nested
    @DisplayName("playerStatistics가 적절한 값을 갖는지 테스트")
    inner class PlayerStatistics {
        @Test
        fun `10000원을 배팅하고 이기면 플레이어는 10000원을 얻는다`() {
            val dealer = buildDealer(CardNumber.THREE)
            val player1 = buildPlayer(CardNumber.FOUR)

            val statistics = GameStatistics(dealer, listOf(player1))
            val actual = statistics.playerStatistics
            val expected = mapOf("플레이어" to 10000L)
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `10000원을 배팅하고 지면 플레이어는 10000원을 잃는다`() {
            val dealer = buildDealer(CardNumber.FOUR)
            val player1 = buildPlayer(CardNumber.THREE)

            val statistics = GameStatistics(dealer, listOf(player1))
            val actual = statistics.playerStatistics
            val expected = mapOf("플레이어" to -10000L)
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `10000원을 배팅하고 비기면 플레이어는 0원을 얻는다`() {
            val dealer = buildDealer(CardNumber.FOUR)
            val player1 = buildPlayer(CardNumber.FOUR)

            val statistics = GameStatistics(dealer, listOf(player1))
            val actual = statistics.playerStatistics
            val expected = mapOf("플레이어" to 0L)
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `10000원을 배팅하고 블랙잭이면 플레이어는 15000원을 얻는다`() {
            val dealer = buildDealer(CardNumber.FOUR)
            val player1 = buildPlayer(CardNumber.TEN, CardNumber.ACE)

            val statistics = GameStatistics(dealer, listOf(player1))
            val actual = statistics.playerStatistics
            val expected = mapOf("플레이어" to 15000L)
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `10000원을 배팅한 플레이어 2명이 지면 각각 10000원을 잃는다`() {
            val dealer = buildDealer(CardNumber.FOUR)

            val player1 = buildPlayer(CardNumber.THREE)
            val player2 = buildPlayer2(CardNumber.THREE)

            val statistics = GameStatistics(dealer, listOf(player1, player2))
            val actual = statistics.playerStatistics
            val expected = mapOf("플레이어" to -10000L, "플레이어2" to -10000L)
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `10000원을 배팅한 플레이어 2명이 이기면 각각 10000원을 얻는다`() {
            val dealer = buildDealer(CardNumber.TWO)

            val player1 = buildPlayer(CardNumber.THREE)
            val player2 = buildPlayer2(CardNumber.THREE)

            val statistics = GameStatistics(dealer, listOf(player1, player2))
            val actual = statistics.playerStatistics
            val expected = mapOf("플레이어" to 10000L, "플레이어2" to 10000L)
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `플레이어 2명이 모두 비겼을 때 각각 0원을 얻는다`() {
            val dealer = buildDealer(CardNumber.THREE)

            val player1 = buildPlayer(CardNumber.THREE)
            val player2 = buildPlayer2(CardNumber.THREE)

            val statistics = GameStatistics(dealer, listOf(player1, player2))
            val actual = statistics.playerStatistics
            val expected = mapOf("플레이어" to 0L, "플레이어2" to 0L)
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `플레이어 1명이 이기고 1명이 졌을 때 각각 10000원을 얻고, 잃는다`() {
            val dealer = buildDealer(CardNumber.THREE)

            val player1 = buildPlayer(CardNumber.FOUR)
            val player2 = buildPlayer2(CardNumber.TWO)

            val statistics = GameStatistics(dealer, listOf(player1, player2))
            val actual = statistics.playerStatistics
            val expected = mapOf("플레이어" to 10000L, "플레이어2" to -10000L)
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `딜러가 블랙잭이면 10000원을 배팅한 플레이어 2명이 각각 10000원을 잃는다`() {
            val dealer = buildDealer(CardNumber.TEN, CardNumber.ACE)

            val player1 = buildPlayer(CardNumber.TEN, CardNumber.TEN)
            val player2 = buildPlayer2(CardNumber.TEN, CardNumber.TEN)

            val statistics = GameStatistics(dealer, listOf(player1, player2))
            val actual = statistics.playerStatistics
            val expected = mapOf("플레이어" to -10000L, "플레이어2" to -10000L)
            assertThat(actual).isEqualTo(expected)
        }
    }

    fun buildPlayer(vararg cardNumber: CardNumber): Player {
        val player = Player("플레이어", 10000)
        val cardList = listOf(*cardNumber)
        cardList.forEach {
            player.addCard(Card(it, Suit.HEART))
        }
        return player
    }

    fun buildPlayer2(vararg cardNumber: CardNumber): Player {
        val player = Player("플레이어2", 10000)
        val cardList = listOf(*cardNumber)
        cardList.forEach {
            player.addCard(Card(it, Suit.HEART))
        }
        return player
    }

    fun buildDealer(vararg cardNumber: CardNumber): Dealer {
        val dealer = Dealer()
        val cardList = listOf(*cardNumber)
        cardList.forEach {
            dealer.addCard(Card(it, Suit.HEART))
        }
        return dealer
    }
}
