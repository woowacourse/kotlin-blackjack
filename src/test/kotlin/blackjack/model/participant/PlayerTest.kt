package blackjack.model.participant

import blackjack.model.card.Card
import blackjack.model.card.CardShape
import blackjack.model.card.Denomination
import blackjack.model.state.Hit
import blackjack.model.state.Stay
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PlayerTest {
    @Test
    fun `플레이어의 이름이 딜러이면 에러를 반환한다`() {
        assertThrows<IllegalArgumentException> { Player("딜러") }
    }

    @ParameterizedTest
    @ValueSource(strings = ["가나다라마바사", ""])
    fun `플레이어의 이름이 1~5자를 넘어가면 에러를 반환한다`(name: String) {
        assertThrows<IllegalArgumentException> { Player(name) }
    }

    @Test
    fun `상태를 업데이트 할수 있다`() {
        val player = Player("모찌")
        val card = Card(CardShape.HEART, Denomination.NINE)
        player.receiveCard(card)

        val actual = player.state

        Assertions.assertThat(actual).isInstanceOf(Hit::class.java)
    }

    @Test
    fun `상태를 Finish로 업데이트 할수 있다`() {
        val player = Player("모찌")
        val card = Card(CardShape.HEART, Denomination.NINE)
        player.receiveCard(card)
        player.stop()

        val actual = player.state

        Assertions.assertThat(actual).isInstanceOf(Stay::class.java)
    }
}
