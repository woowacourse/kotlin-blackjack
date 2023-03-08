package domain.participants

import domain.card.Card
import domain.card.CardValue
import domain.card.Cards
import domain.card.Shape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {

    @Test
    fun `플레이어 카드의 합이 21이 넘었을 경우 true를 반환한다`() {
        val cards = Cards(
            mutableListOf(
                Card(Shape.SPADE, CardValue.JACK),
                Card(Shape.SPADE, CardValue.JACK),
                Card(Shape.SPADE, CardValue.JACK)
            )
        )
        val player = Player("pingu", cards)
        assertThat(player.checkBurst()).isTrue
    }

    @Test
    fun `플레이어 카드의 합이 21이 넘지 않았을 경우 false를 반환한다`() {
        val cards = Cards(mutableListOf(Card(Shape.SPADE, CardValue.JACK), Card(Shape.SPADE, CardValue.JACK)))
        val player = Player("pingu", cards)
        assertThat(player.checkBurst()).isFalse
    }
}
