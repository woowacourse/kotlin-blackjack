package domain.person

import domain.card.Card
import domain.card.CardNumber
import domain.card.CardShape
import domain.card.Hand
import domain.money.Money
import domain.money.Profit
import domain.state.BlackJack
import domain.state.Stay
import domain.state.dealerState.DealerFirstTurn
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {

    @Test
    fun `플레이어는 TEN 과 KING 을 받으면 Started 상태이다`() {
        val player = Player("베르")

        player.receiveCard(
            listOf(
                Card(CardShape.HEART, CardNumber.TEN),
                Card(CardShape.HEART, CardNumber.KING),
            ),
        )

        assertThat(player.isStarted()).isTrue
    }

    @Test
    fun `플레이어는 TEN 과 KING 과 JACK 을 받으면 Finished 상태이다`() {
        val player = Player("베르")

        player.receiveCard(
            listOf(
                Card(CardShape.HEART, CardNumber.TEN),
                Card(CardShape.HEART, CardNumber.KING),
                Card(CardShape.HEART, CardNumber.JACK),
            ),
        )

        assertThat(player.isFinished()).isTrue
    }

    @Test
    fun `플레이어는 TEN 과 KING 과 ACE 을 받으면 BlackJack 상태이다`() {
        val player = Player("베르")

        player.receiveCard(
            listOf(
                Card(CardShape.HEART, CardNumber.KING),
                Card(CardShape.HEART, CardNumber.ACE),
            ),
        )

        assertThat(player.state is BlackJack).isTrue
    }

    @Test
    fun `플레이어는 KING 과 TEN 을 받고 STAY 하면 STAY 상태이다`() {
        val player = Player("베르")

        player.receiveCard(
            listOf(
                Card(CardShape.HEART, CardNumber.KING),
                Card(CardShape.HEART, CardNumber.TEN),
            ),
        )

        player.stay()

        assertThat(player.state is Stay).isTrue
    }

    @Test
    fun `플레이어가 Stay 하면 상태가 Stay 된다`() {
        val player = Player("베르")
        player.receiveCard(
            listOf(
                Card(CardShape.HEART, CardNumber.KING),
                Card(CardShape.DIAMOND, CardNumber.QUEEN),
            ),
        )

        player.stay()

        assertThat(player.state is Stay).isTrue
    }

    @Test
    fun `플레이어가 Stay 로 이기면 수익이 1배이다`() {
        val player = Player("베르")
        player.receiveCard(
            listOf(
                Card(CardShape.HEART, CardNumber.KING),
                Card(CardShape.DIAMOND, CardNumber.QUEEN),
            ),
        )

        player.stay()

        val otherHand = Hand(Card(CardShape.HEART, CardNumber.SEVEN))
        val other = DealerFirstTurn(otherHand).draw(Card(CardShape.DIAMOND, CardNumber.TEN))

        assertThat(player.getPlayerProfit(other, Money(10000))).isEqualTo(Profit(10000.0))
    }
}
