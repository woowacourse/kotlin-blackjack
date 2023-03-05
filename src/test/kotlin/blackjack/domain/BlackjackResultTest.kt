package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class BlackjackResultTest {

    @Test
    fun `딜러가 히트해야 할 때 블랙잭 결과를 생성하려 하면 에러가 발생한다`() {
        val dealer = Dealer().apply {
            receive(Card(CardNumber.NINE, CardShape.CLOVER))
            receive(Card(CardNumber.TWO, CardShape.CLOVER))
        }
        val players = listOf(
            Player("pobi").apply {
                receive(Card(CardNumber.NINE, CardShape.CLOVER))
                receive(Card(CardNumber.TWO, CardShape.CLOVER))
            },
            Player("thomas").apply {
                receive(Card(CardNumber.NINE, CardShape.CLOVER))
                receive(Card(CardNumber.TWO, CardShape.CLOVER))
            },
        )

        assertThatIllegalArgumentException().isThrownBy { BlackjackResult.of(dealer, players) }
            .withMessage("딜러가 히트해야 한다면 블랙잭 결과를 생성할 수 없습니다.")
    }

    @Test
    fun `플레이어가 한 명도 없을 때 블랙잭 결과를 생성하려 하면 에러가 발생한다`() {
        val dealer = Dealer().apply {
            receive(Card(CardNumber.NINE, CardShape.CLOVER))
            receive(Card(CardNumber.TEN, CardShape.CLOVER))
        }
        val players = listOf<Player>()

        assertThatIllegalArgumentException().isThrownBy { BlackjackResult.of(dealer, players) }
            .withMessage("플레이어가 없다면 블랙잭 결과를 생성할 수 없습니다.")
    }

    @Test
    fun `참여자 중 2장 이상의 카드를 받지 않은 참여자가 있을 때 블랙잭 결과를 생성하려 하면 에러가 발생한다`() {
        val dealer = Dealer().apply {
            receive(Card(CardNumber.NINE, CardShape.CLOVER))
            receive(Card(CardNumber.TEN, CardShape.CLOVER))
        }
        val players = listOf(
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
        )

        assertThatIllegalArgumentException().isThrownBy { BlackjackResult.of(dealer, players) }
            .withMessage("모든 참여자는 ${Participant.INIT_CARD_SIZE}장 이상의 카드를 가지고 있어야 블랙잭 결과를 생성할 수 있습니다.")
    }

    @Test
    fun `딜러와 플레이어들이 주어지면 딜러의 승무패 각각의 횟수를 알 수 있다`() {
        val dealer = Dealer().apply {
            receive(Card(CardNumber.NINE, CardShape.CLOVER))
            receive(Card(CardNumber.TEN, CardShape.CLOVER))
        }
        val players = listOf(
            Player("pobi").apply {
                receive(Card(CardNumber.NINE, CardShape.CLOVER))
                receive(Card(CardNumber.TWO, CardShape.CLOVER))
            },
            Player("thomas").apply {
                receive(Card(CardNumber.KING, CardShape.CLOVER))
                receive(Card(CardNumber.ACE, CardShape.CLOVER))
            },
            Player("jason").apply {
                receive(Card(CardNumber.NINE, CardShape.CLOVER))
                receive(Card(CardNumber.TEN, CardShape.CLOVER))
            },
        )

        val result = BlackjackResult.of(dealer, players)

        assertAll(
            { assertThat(result.getCountOfDealer(ResultType.WIN)).isEqualTo(1) },
            { assertThat(result.getCountOfDealer(ResultType.TIE)).isEqualTo(1) },
            { assertThat(result.getCountOfDealer(ResultType.LOSE)).isEqualTo(1) },
        )
    }

    @Test
    fun `딜러와 플레이어들이 주어지면 플레이어의 딜러와의 승부 결과를 알 수 있다`() {
        val dealer = Dealer().apply {
            receive(Card(CardNumber.NINE, CardShape.CLOVER))
            receive(Card(CardNumber.TEN, CardShape.CLOVER))
        }
        val players = listOf(
            Player("pobi").apply {
                receive(Card(CardNumber.NINE, CardShape.CLOVER))
                receive(Card(CardNumber.TWO, CardShape.CLOVER))
            },
            Player("thomas").apply {
                receive(Card(CardNumber.KING, CardShape.CLOVER))
                receive(Card(CardNumber.ACE, CardShape.CLOVER))
            },
            Player("jason").apply {
                receive(Card(CardNumber.NINE, CardShape.CLOVER))
                receive(Card(CardNumber.TEN, CardShape.CLOVER))
            },
        )

        val result = BlackjackResult.of(dealer, players)

        assertAll(
            { assertThat(result.getResultOf(players[0])).isEqualTo(ResultType.LOSE) },
            { assertThat(result.getResultOf(players[1])).isEqualTo(ResultType.WIN) },
            { assertThat(result.getResultOf(players[2])).isEqualTo(ResultType.TIE) },
        )
    }
}
