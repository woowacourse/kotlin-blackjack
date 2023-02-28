package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어는 소지한 카드의 합은 8이다`() {
        // given
        val player = Player(
            listOf<Card>(
                Card(Card.Shape.CLUBS, Card.Value.FIVE),
                Card(Card.Shape.DIAMONDS, Card.Value.THREE),
            ),
        )

        // when
        val actual = player.calculateCardValueSum()

        // then
        assertThat(actual).isEqualTo(8)
    }

    @Test
    fun `플레이어는 소지한 카드의 합은 10이다`() {
        // given
        val player = Player(
            listOf<Card>(
                Card(Card.Shape.CLUBS, Card.Value.FIVE),
                Card(Card.Shape.DIAMONDS, Card.Value.FIVE),
            ),
        )

        // when
        val actual = player.calculateCardValueSum()

        // then
        assertThat(actual).isEqualTo(10)
    }
}
