package blackjack.model

// import org.assertj.core.api.Assertions.assertThat
// import org.junit.jupiter.api.DisplayName
// import org.junit.jupiter.api.Nested
// import org.junit.jupiter.api.Test

class GameStatisticsTest {
//    @Nested
//    @DisplayName("dealerStatistics가 적절한 값을 갖는지 테스트")
//    inner class DealerStatisticsTest {
//        @Test
//        fun `플레이어 1명이 졌을 때 딜러는 1번 승리한다`() {
//            val dealer = buildDealer(CardNumber.FOUR)
//
//            val player1 = buildPlayer("a", CardNumber.THREE)
//
//            val statistics = GameStatistics(dealer, listOf(player1))
//            val actual = statistics.dealerStatistics
//            val expected = mapOf(GameResult.WIN to 1)
//            assertThat(actual).isEqualTo(expected)
//        }
//
//        @Test
//        fun `플레이어 2명이 졌을 때 딜러는 2번 승리한다`() {
//            val dealer = buildDealer(CardNumber.FOUR)
//
//            val player1 = buildPlayer("a", CardNumber.THREE)
//            val player2 = buildPlayer("b", CardNumber.THREE)
//
//            val statistics = GameStatistics(dealer, listOf(player1, player2))
//            val actual = statistics.dealerStatistics
//            val expected = mapOf(GameResult.WIN to 2)
//            assertThat(actual).isEqualTo(expected)
//        }
//
//        @Test
//        fun `플레이어 2명이 이겼을 때 딜러는 2번 패배한다`() {
//            val dealer = buildDealer(CardNumber.TWO)
//
//            val player1 = buildPlayer("a", CardNumber.THREE)
//            val player2 = buildPlayer("b", CardNumber.THREE)
//
//            val statistics = GameStatistics(dealer, listOf(player1, player2))
//            val actual = statistics.dealerStatistics
//            val expected = mapOf(GameResult.LOSE to 2)
//            assertThat(actual).isEqualTo(expected)
//        }
//
//        @Test
//        fun `플레이어 2명이 비겼을 때 딜러는 2번 비긴다`() {
//            val dealer = buildDealer(CardNumber.THREE)
//
//            val player1 = buildPlayer("a", CardNumber.THREE)
//            val player2 = buildPlayer("b", CardNumber.THREE)
//
//            val statistics = GameStatistics(dealer, listOf(player1, player2))
//            val actual = statistics.dealerStatistics
//            val expected = mapOf(GameResult.DRAW to 2)
//            assertThat(actual).isEqualTo(expected)
//        }
//
//        @Test
//        fun `플레이어 1명이 이기고 1명이 졌을 때 딜러는 1번 승리하고 1번 패배한다`() {
//            val dealer = buildDealer(CardNumber.THREE)
//
//            val player1 = buildPlayer("a", CardNumber.TWO)
//            val player2 = buildPlayer("b", CardNumber.FOUR)
//
//            val statistics = GameStatistics(dealer, listOf(player1, player2))
//            val actual = statistics.dealerStatistics
//            val expected = mapOf(GameResult.WIN to 1, GameResult.LOSE to 1)
//            assertThat(actual).isEqualTo(expected)
//        }
//    }
//
//    @Test
//    fun `딜러와 플레이어를 받아 플레이어들의 통계를 생성한다`() {
//        val dealer = buildDealer(CardNumber.FOUR)
//
//        val player1 = buildPlayer("a", CardNumber.THREE)
//
//        val statistics = GameStatistics(dealer, listOf(player1))
//        val actual = statistics.playerStatistics
//        val expected = mapOf("a" to GameResult.LOSE)
//        assertThat(actual).isEqualTo(expected)
//    }
//
//    @Nested
//    @DisplayName("승패 판정 테스트")
//    inner class JudgeTest {
//        @Test
//        fun `플레이어만 bust될 때 무조건 패배한다`() {
//            val dealer = buildDealer(CardNumber.FOUR)
//
//            val player1 = buildPlayer("a", CardNumber.JACK, CardNumber.QUEEN, CardNumber.KING)
//
//            val statistics = GameStatistics(dealer, listOf(player1))
//            val actual = statistics.playerStatistics
//            val expected = mapOf("a" to GameResult.LOSE)
//            assertThat(actual).isEqualTo(expected)
//        }
//
//        @Test
//        fun `딜러와 플레이어가 둘 다 bust될 때 플레이어가 패배한다`() {
//            val dealer = buildDealer(CardNumber.JACK, CardNumber.QUEEN, CardNumber.KING)
//
//            val player1 = buildPlayer("a", CardNumber.JACK, CardNumber.QUEEN, CardNumber.KING)
//
//            val statistics = GameStatistics(dealer, listOf(player1))
//            val actual = statistics.playerStatistics
//            val expected = mapOf("a" to GameResult.LOSE)
//            assertThat(actual).isEqualTo(expected)
//        }
//
//        @Test
//        fun `둘 다 블랙잭인 경우 무승부로 한다`() {
//            val dealer = buildDealer(CardNumber.JACK, CardNumber.ACE)
//
//            val player1 = buildPlayer("a", CardNumber.JACK, CardNumber.ACE)
//
//            val statistics = GameStatistics(dealer, listOf(player1))
//            val actual = statistics.playerStatistics
//            val expected = mapOf("a" to GameResult.DRAW)
//            assertThat(actual).isEqualTo(expected)
//        }
//
//        @Test
//        fun `딜러가 블랙잭이고 플레이어가 21인 경우 플레이어가 패배하고 딜러는 1번 승리한다`() {
//            val dealer = buildDealer(CardNumber.JACK, CardNumber.ACE)
//            val player1 = buildPlayer("a", CardNumber.JACK, CardNumber.FIVE, CardNumber.SIX)
//            val statistics = GameStatistics(dealer, listOf(player1))
//
//            val actualPlayer = statistics.playerStatistics
//            val expectedPlayer = mapOf("a" to GameResult.LOSE)
//            assertThat(actualPlayer).isEqualTo(expectedPlayer)
//
//            val actualDealer = statistics.dealerStatistics
//            val expectedDealer = mapOf(GameResult.WIN to 1)
//            assertThat(actualDealer).isEqualTo(expectedDealer)
//        }
//
//        @Test
//        fun `플레이어가 블랙잭이고 딜러가 21인 경우 승리한다`() {
//            val dealer = buildDealer(CardNumber.JACK, CardNumber.FIVE, CardNumber.SIX)
//
//            val player1 = buildPlayer("a", CardNumber.JACK, CardNumber.ACE)
//
//            val statistics = GameStatistics(dealer, listOf(player1))
//            val actual = statistics.playerStatistics
//            val expected = mapOf("a" to GameResult.WIN)
//            assertThat(actual).isEqualTo(expected)
//        }
//
//        @Test
//        fun `모두 블랙잭이 아니고 플레이어의 점수가 클 경우 플레이어가 승리한다`() {
//            val dealer = buildDealer(CardNumber.TWO, CardNumber.THREE)
//
//            val player1 = buildPlayer("a", CardNumber.FOUR, CardNumber.FIVE)
//
//            val statistics = GameStatistics(dealer, listOf(player1))
//            val actual = statistics.playerStatistics
//            val expected = mapOf("a" to GameResult.WIN)
//            assertThat(actual).isEqualTo(expected)
//        }
//
//        @Test
//        fun `모두 블랙잭이 아니고 플레이어의 점수가 작을 경우 플레이어가 패배한다`() {
//            val dealer = buildDealer(CardNumber.FOUR, CardNumber.FIVE)
//
//            val player1 = buildPlayer("a", CardNumber.TWO, CardNumber.THREE)
//
//            val statistics = GameStatistics(dealer, listOf(player1))
//            val actual = statistics.playerStatistics
//            val expected = mapOf("a" to GameResult.LOSE)
//            assertThat(actual).isEqualTo(expected)
//        }
//
//        @Test
//        fun `모두 블랙잭이 아니고 점수가 같을 경우 무승부로 한다`() {
//            val dealer = buildDealer(CardNumber.TWO, CardNumber.THREE)
//
//            val player1 = buildPlayer("a", CardNumber.TWO, CardNumber.THREE)
//
//            val statistics = GameStatistics(dealer, listOf(player1))
//            val actual = statistics.playerStatistics
//            val expected = mapOf("a" to GameResult.DRAW)
//            assertThat(actual).isEqualTo(expected)
//        }
//
//        @Test
//        fun `딜러만 버스트 된 경우 플레이어가 승리한다`() {
//            val dealer = buildDealer(CardNumber.SIX, CardNumber.TEN, CardNumber.NINE)
//
//            val player1 = buildPlayer("a", CardNumber.FIVE, CardNumber.SEVEN, CardNumber.SIX)
//            val player2 = buildPlayer("b", CardNumber.ACE, CardNumber.THREE, CardNumber.EIGHT)
//
//            val statistics = GameStatistics(dealer, listOf(player1, player2))
//            val actual = statistics.playerStatistics
//            val expected = mapOf("a" to GameResult.WIN, "b" to GameResult.WIN)
//            assertThat(actual).isEqualTo(expected)
//        }
//    }
//
//    fun buildPlayer(
//        name: String,
//        vararg cardNumber: CardNumber,
//    ): Player {
//        val player = Player(name, 0)
//        val cardList = listOf(*cardNumber)
//        cardList.forEach {
//            player.addCard(Card(it, Suit.HEART))
//        }
//        return player
//    }
//
//    fun buildDealer(vararg cardNumber: CardNumber): Dealer {
//        val dealer = Dealer()
//        val cardList = listOf(*cardNumber)
//        cardList.forEach {
//            dealer.addCard(Card(it, Suit.HEART))
//        }
//        return dealer
//    }
}
