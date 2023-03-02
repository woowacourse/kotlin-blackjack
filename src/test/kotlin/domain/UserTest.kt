package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class UserTest {
    @Test
    fun `플레이어는 소지한 카드의 합은 8이다`() {
        // given
        val user = User.create(
            "산군" to listOf<Card>(
                Card(Card.Shape.CLUBS, Card.Value.FIVE),
                Card(Card.Shape.DIAMONDS, Card.Value.THREE),
            ),
        )

        // when
        val actual = user.calculateCardValueSum()

        // then
        assertThat(actual).isEqualTo(8)
    }

    @Test
    fun `플레이어는 소지한 카드의 합은 10이다`() {
        // given
        val user = User.create(
            "산군" to
                listOf<Card>(
                    Card(Card.Shape.CLUBS, Card.Value.FIVE),
                    Card(Card.Shape.DIAMONDS, Card.Value.FIVE),
                ),
        )

        // when
        val actual = user.calculateCardValueSum()

        // then
        assertThat(actual).isEqualTo(10)
    }

    @Test
    fun `카드 한장을 추가한다`() {
        // given
        val user = User.create(
            "산군" to
                listOf<Card>(
                    Card(Card.Shape.CLUBS, Card.Value.FIVE),
                    Card(Card.Shape.DIAMONDS, Card.Value.FIVE),
                ),
        )
        val card = CardMachine.getNewCard()

        // when
        user.addCard(card)
        val actual = user.cards

        // then
        assertThat(actual).hasSize(3)
    }
}
