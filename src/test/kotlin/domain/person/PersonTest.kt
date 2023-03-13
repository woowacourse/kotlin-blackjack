package domain.person

import domain.card.Card
import domain.card.CardNumber
import domain.card.CardShape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PersonTest {
    @Test
    fun `Person 은 이름을 가진다`() {
        val player = Player("베르")

        assertThat(player.name).isEqualTo("베르")
    }

    @Test
    fun `Person 은 카드를 받아서 추가할 수 있다`() {
        val player = Player("베르")

        player.receiveCard(
            listOf(
                Card(CardShape.HEART, CardNumber.TEN),
                Card(CardShape.HEART, CardNumber.KING),
            ),
        )

        assertThat(player.state.getHandCards()).isEqualTo(
            listOf(
                Card(CardShape.HEART, CardNumber.TEN),
                Card(CardShape.HEART, CardNumber.KING),
            ),
        )
    }

    @Test
    fun `Person 의 카드를 확인할 수 있다`() {
        val player = Player("베르")

        player.receiveCard(
            listOf(
                Card(CardShape.HEART, CardNumber.KING),
                Card(CardShape.HEART, CardNumber.TEN),
            ),
        )

        assertThat(player.state.getHandCards()).isEqualTo(
            listOf(
                Card(CardShape.HEART, CardNumber.KING),
                Card(CardShape.HEART, CardNumber.TEN),
            ),
        )
    }
}
