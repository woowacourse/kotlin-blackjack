package domain.gamer.state

import domain.card.Card
import domain.card.CardValue
import domain.card.Shape
import domain.gamer.Player
import domain.gamer.cards.Cards
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerStateTest {
    @Test
    fun `뽑은 카드를 저장한다`() {
        val playerState = Player("jack", Cards(listOf()))
        playerState.pickCard(Card(Shape.CLOVER, CardValue.JACK))
        assertThat(playerState.cards.getCards()).isEqualTo(listOf(Card(Shape.CLOVER, CardValue.JACK)))
    }
}
