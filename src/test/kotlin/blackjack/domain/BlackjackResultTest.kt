package blackjack.domain

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class BlackjackResultTest {

    @Test
    fun `딜러가 끝난 상태가 아닐 때 블랙잭 결과를 생성하려 하면 에러가 발생한다`() {
        val dealer = Dealer().apply {
            receive(Card(CardNumber.NINE, CardShape.CLOVER))
            receive(Card(CardNumber.TWO, CardShape.CLOVER))
        }
        val players = Players(
            listOf(
                Player("pobi").apply {
                    betting(Money(10000))
                    receive(Card(CardNumber.NINE, CardShape.CLOVER))
                    receive(Card(CardNumber.TWO, CardShape.CLOVER))
                    stay()
                },
                Player("thomas").apply {
                    betting(Money(10000))
                    receive(Card(CardNumber.NINE, CardShape.CLOVER))
                    receive(Card(CardNumber.TWO, CardShape.CLOVER))
                    stay()
                },
            ),
        )

        assertThatIllegalArgumentException().isThrownBy { BlackjackResult.of(dealer, players) }
            .withMessage("모든 참여자는 게임이 끝난 상태여야 합니다.")
    }

    @Test
    fun `플레이어 중 끝난 상태가 아닌 플레이어가 있을 때 블랙잭 결과를 생성하면 에러가 발생한다`() {
        val dealer = Dealer().apply {
            receive(Card(CardNumber.NINE, CardShape.CLOVER))
            receive(Card(CardNumber.TEN, CardShape.CLOVER))
        }
        val players = Players(
            listOf(
                Player("pobi").apply {
                    betting(Money(10000))
                    receive(Card(CardNumber.NINE, CardShape.CLOVER))
                    receive(Card(CardNumber.NINE, CardShape.HEART))
                    stay()
                },
                Player("thomas").apply {
                    betting(Money(10000))
                    receive(Card(CardNumber.KING, CardShape.CLOVER))
                    receive(Card(CardNumber.KING, CardShape.HEART))
                    stay()
                },
                Player("jason").apply {
                    betting(Money(10000))
                    receive(Card(CardNumber.NINE, CardShape.CLOVER))
                    receive(Card(CardNumber.TEN, CardShape.CLOVER))
                },
            ),
        )

        assertThatIllegalArgumentException().isThrownBy { BlackjackResult.of(dealer, players) }
            .withMessage("모든 참여자는 게임이 끝난 상태여야 합니다.")
    }

    @Test
    fun `플레이어가 딜러에게 승리했다면 플레이어의 수익은 플레이어의 상태의 수익과 같다`() {
        val dealer = Dealer().apply {
            receive(Card(CardNumber.NINE, CardShape.CLOVER))
            receive(Card(CardNumber.TEN, CardShape.CLOVER))
        }
        val players = Players(
            listOf(
                Player("pobi").apply {
                    betting(Money(10000))
                    receive(Card(CardNumber.TEN, CardShape.CLOVER))
                    receive(Card(CardNumber.TEN, CardShape.HEART))
                    stay()
                },
                Player("thomas").apply {
                    betting(Money(10000))
                    receive(Card(CardNumber.KING, CardShape.CLOVER))
                    receive(Card(CardNumber.KING, CardShape.HEART))
                    stay()
                },
            ),
        )

        val actual = BlackjackResult.of(dealer, players).getRevenueOf(players.toList()[0])
        val expect = players.toList()[1].getProfit().toInt()

        assertThat(actual).isEqualTo(expect)
    }

    @Test
    fun `플레이어와 딜러가 무승부라면 플레이어의 수익은 0이다`() {
        val dealer = Dealer().apply {
            receive(Card(CardNumber.NINE, CardShape.CLOVER))
            receive(Card(CardNumber.TEN, CardShape.CLOVER))
        }
        val players = Players(
            listOf(
                Player("pobi").apply {
                    betting(Money(10000))
                    receive(Card(CardNumber.NINE, CardShape.CLOVER))
                    receive(Card(CardNumber.TEN, CardShape.HEART))
                    stay()
                },
                Player("thomas").apply {
                    betting(Money(10000))
                    receive(Card(CardNumber.KING, CardShape.CLOVER))
                    receive(Card(CardNumber.KING, CardShape.HEART))
                    stay()
                },
            ),
        )

        val actual = BlackjackResult.of(dealer, players).getRevenueOf(players.toList()[0])

        assertThat(actual).isZero
    }

    @Test
    fun `플레이어가 딜러에게 패배했다면 플레이어의 수익은 베팅 금액의 음수이다`() {
        val dealer = Dealer().apply {
            receive(Card(CardNumber.NINE, CardShape.CLOVER))
            receive(Card(CardNumber.TEN, CardShape.CLOVER))
        }
        val players = Players(
            listOf(
                Player("pobi").apply {
                    betting(Money(10000))
                    receive(Card(CardNumber.NINE, CardShape.CLOVER))
                    receive(Card(CardNumber.NINE, CardShape.HEART))
                    stay()
                },
                Player("thomas").apply {
                    betting(Money(10000))
                    receive(Card(CardNumber.KING, CardShape.CLOVER))
                    receive(Card(CardNumber.KING, CardShape.HEART))
                    stay()
                },
            ),
        )

        val actual = BlackjackResult.of(dealer, players).getRevenueOf(players.toList()[0])
        val expect = -10000

        assertThat(actual).isEqualTo(expect)
    }

    @Test
    fun `딜러의 수익은 플레이어의 수익의 합의 음수와 같다`() {
        val dealer = Dealer().apply {
            receive(Card(CardNumber.NINE, CardShape.CLOVER))
            receive(Card(CardNumber.TEN, CardShape.CLOVER))
        }
        val players = Players(
            listOf(
                Player("pobi").apply {
                    betting(Money(10000))
                    receive(Card(CardNumber.NINE, CardShape.CLOVER))
                    receive(Card(CardNumber.NINE, CardShape.HEART))
                    stay()
                },
                Player("thomas").apply {
                    betting(Money(10000))
                    receive(Card(CardNumber.KING, CardShape.CLOVER))
                    receive(Card(CardNumber.KING, CardShape.HEART))
                    stay()
                },
                Player("jason").apply {
                    betting(Money(10000))
                    receive(Card(CardNumber.NINE, CardShape.CLOVER))
                    receive(Card(CardNumber.TEN, CardShape.CLOVER))
                    stay()
                },
            ),
        )

        val actual = BlackjackResult.of(dealer, players).dealerRevenue
        val expect = -players.toList().sumOf { BlackjackResult.of(dealer, players).getRevenueOf(it)!! }

        assertThat(actual).isEqualTo(expect)
    }
}
