package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test

class BlackjackResultTest {

    @Test
    fun `딜러가 히트해야 할 때 블랙잭 결과를 생성하려 하면 에러가 발생한다`() {
        val dealer = Dealer().apply {
            receive(Card(CardNumber.NINE, CardShape.CLOVER))
            receive(Card(CardNumber.TWO, CardShape.CLOVER))
        }
        val playersBetAmount = Players(
            listOf(
                Player("pobi").apply {
                    receive(Card(CardNumber.NINE, CardShape.CLOVER))
                    receive(Card(CardNumber.TWO, CardShape.CLOVER))
                },
                Player("thomas").apply {
                    receive(Card(CardNumber.NINE, CardShape.CLOVER))
                    receive(Card(CardNumber.TWO, CardShape.CLOVER))
                },
            ),
        ).associateWith { Money(10000) }

        assertThatIllegalArgumentException().isThrownBy { BlackjackResult.of(dealer, playersBetAmount) }
            .withMessage("딜러가 히트해야 한다면 블랙잭 결과를 생성할 수 없습니다.")
    }

    @Test
    fun `참여자 중 2장 이상의 카드를 받지 않은 참여자가 있을 때 블랙잭 결과를 생성하려 하면 에러가 발생한다`() {
        val dealer = Dealer().apply {
            receive(Card(CardNumber.NINE, CardShape.CLOVER))
            receive(Card(CardNumber.TEN, CardShape.CLOVER))
        }
        val playersBetAmount = Players(
            listOf(
                Player("pobi").apply {
                    receive(Card(CardNumber.NINE, CardShape.CLOVER))
                },
                Player("thomas").apply {
                    receive(Card(CardNumber.KING, CardShape.CLOVER))
                    receive(Card(CardNumber.ACE, CardShape.CLOVER))
                },
                Player("jason").apply {
                    receive(Card(CardNumber.NINE, CardShape.CLOVER))
                    receive(Card(CardNumber.TEN, CardShape.CLOVER))
                },
            ),
        ).associateWith { Money(10000) }

        assertThatIllegalArgumentException().isThrownBy { BlackjackResult.of(dealer, playersBetAmount) }
            .withMessage("모든 참여자는 ${Participant.INIT_CARD_SIZE}장 이상의 카드를 가지고 있어야 블랙잭 결과를 생성할 수 있습니다.")
    }

    @Test
    fun `플레이어가 딜러에게 패배했다면 수익은 베팅 금액의 음수이다`() {
        val player1 = Player("pobi").apply {
            receive(Card(CardNumber.TWO, CardShape.DIAMOND))
            receive(Card(CardNumber.TWO, CardShape.HEART))
        }
        val player2 = Player("thomas").apply {
            receive(Card(CardNumber.TWO, CardShape.DIAMOND))
            receive(Card(CardNumber.TWO, CardShape.HEART))
        }
        val dealer = Dealer().apply {
            receive(Card(CardNumber.TEN, CardShape.DIAMOND))
            receive(Card(CardNumber.ACE, CardShape.HEART))
        }
        val betAmount = 10000
        val playersBetAmount = mapOf(player1 to Money(betAmount), player2 to Money(betAmount))

        val actual = BlackjackResult.of(dealer, playersBetAmount)

        assertThat(actual.getRevenueOf(player1)).isEqualTo(-betAmount)
    }

    @Test
    fun `플레이어가 딜러와 무승부라면 수익은 0이다`() {
        val player1 = Player("pobi").apply {
            receive(Card(CardNumber.TEN, CardShape.DIAMOND))
            receive(Card(CardNumber.ACE, CardShape.HEART))
        }
        val player2 = Player("thomas").apply {
            receive(Card(CardNumber.TWO, CardShape.DIAMOND))
            receive(Card(CardNumber.TWO, CardShape.HEART))
        }
        val dealer = Dealer().apply {
            receive(Card(CardNumber.TEN, CardShape.DIAMOND))
            receive(Card(CardNumber.ACE, CardShape.HEART))
        }
        val betAmount = 10000
        val playersBetAmount = mapOf(player1 to Money(betAmount), player2 to Money(betAmount))

        val actual = BlackjackResult.of(dealer, playersBetAmount)

        assertThat(actual.getRevenueOf(player1)).isEqualTo(0)
    }

    @Test
    fun `플레이어가 딜러에게 승리했고 블랙잭이 아니면 수익은 베팅 금액과 같다`() {
        val player1 = Player("pobi").apply {
            receive(Card(CardNumber.TEN, CardShape.DIAMOND))
            receive(Card(CardNumber.TEN, CardShape.HEART))
        }
        val player2 = Player("thomas").apply {
            receive(Card(CardNumber.TWO, CardShape.DIAMOND))
            receive(Card(CardNumber.TWO, CardShape.HEART))
        }
        val dealer = Dealer().apply {
            receive(Card(CardNumber.TEN, CardShape.DIAMOND))
            receive(Card(CardNumber.SEVEN, CardShape.HEART))
        }
        val betAmount = 10000
        val playersBetAmount = mapOf(player1 to Money(betAmount), player2 to Money(betAmount))

        val actual = BlackjackResult.of(dealer, playersBetAmount)

        assertThat(actual.getRevenueOf(player1)).isEqualTo(betAmount)
    }

    @Test
    fun `플레이어가 딜러에게 승리했고 블랙잭이면 수익은 베팅 금액에 베팅 금액의 절반을 더한 것과 같다`() {
        val player1 = Player("pobi").apply {
            receive(Card(CardNumber.ACE, CardShape.DIAMOND))
            receive(Card(CardNumber.TEN, CardShape.HEART))
        }
        val player2 = Player("thomas").apply {
            receive(Card(CardNumber.TWO, CardShape.DIAMOND))
            receive(Card(CardNumber.TWO, CardShape.HEART))
        }
        val dealer = Dealer().apply {
            receive(Card(CardNumber.TEN, CardShape.DIAMOND))
            receive(Card(CardNumber.SEVEN, CardShape.HEART))
        }
        val betAmount = 10000
        val playersBetAmount = mapOf(player1 to Money(betAmount), player2 to Money(betAmount))

        val actual = BlackjackResult.of(dealer, playersBetAmount)

        assertThat(actual.getRevenueOf(player1)).isEqualTo(betAmount + betAmount / 2)
    }
}
