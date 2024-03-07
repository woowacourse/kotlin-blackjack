package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameStatisticsTest {



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

    @Test
    fun `딜러와 플레이어를 받아 플레이어들의 통계를 생성한다` () {
        val dealer = Dealer()
        dealer.addCard(Card(CardNumber.`4`, Suit.`하트`))

        val player1 = buildPlayer("a", CardNumber.`3`)

        val statistics = GameStatistics(dealer, listOf(player1))
        val actual = statistics.playerStatistics
        val expected = mapOf("a" to GameResult.`패`)
        assertThat(actual).isEqualTo(expected)
    }

    fun buildPlayer(name: String,cardNumber: CardNumber): Player {
        val player = Player(name)
        player.addCard(Card(cardNumber, Suit.`하트`))
        return player
    }
}
