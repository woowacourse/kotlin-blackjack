package domain.gamer

import domain.card.Card
import domain.card.Shape
import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class PlayerTest {
    @ParameterizedTest
    @MethodSource("generateCard")
    fun `뽑은 카드를 저장한다`(cards: List<Card>) {
        val player = Player()
        for (card in cards) {
            player.pickCard(card)
        }
        Assertions.assertThat(player.cards).isEqualTo(cards)
    }

    companion object {
        @JvmStatic
        private fun generateCard(): List<Arguments> =
            listOf(
                Arguments.of(
                    listOf(Card(Shape.SPADE, "A"), Card(Shape.HEART, "2"), Card(Shape.CLOVER, "3")),
                    listOf(Card(Shape.SPADE, "Q"), Card(Shape.SPADE, "3"))
                )
            )
    }
}
