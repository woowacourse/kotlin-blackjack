package domain.person

import domain.card.Card
import domain.card.CardNumber
import domain.card.CardShape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Player2Test {

    @Test
    fun `플레이어는 이름을 가진다`() {
        val player = Player2("베르")

        assertThat(player.name).isEqualTo("베르")
    }

    @Test
    fun `플레이어는 카드를 받아서 추가할 수 있다`() {
        val player = Player2("베르")

        player.receiveCard(
            listOf(
                Card(CardShape.HEART, CardNumber.TEN),
                Card(CardShape.HEART, CardNumber.KING),
            ),
        )

        assertThat(player.getHandCards()).isEqualTo(
            listOf(
                Card(CardShape.HEART, CardNumber.TEN),
                Card(CardShape.HEART, CardNumber.KING),
            ),
        )
    }

    @Test
    fun `플레이어는가 TEN 과 KING 을 받으면 Started 상태이다`() {
        val player = Player2("베르")

        player.receiveCard(
            listOf(
                Card(CardShape.HEART, CardNumber.TEN),
                Card(CardShape.HEART, CardNumber.KING),
            ),
        )

        assertThat(player.isStarted()).isTrue
    }

    @Test
    fun `플레이어는가 TEN 과 KING 과 JACK 을 받으면 Finished 상태이다`() {
        val player = Player2("베르")

        player.receiveCard(
            listOf(
                Card(CardShape.HEART, CardNumber.TEN),
                Card(CardShape.HEART, CardNumber.KING),
                Card(CardShape.HEART, CardNumber.JACK),
            ),
        )

        assertThat(player.isFinished()).isTrue
    }
}
