package domain

import domain.player.User
import domain.card.Card
import domain.card.CardNumber
import domain.card.Shape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class UserTest {
    @Test
    fun `플레이어는 소지한 카드의 합은 8이다`() {
        // given
        val user = User.create(
            "산군",
            listOf<Card>(
                Card.of(Shape.CLUBS, CardNumber.FIVE),
                Card.of(Shape.DIAMONDS, CardNumber.THREE),
            ),
        )

        // when
        val actual = user.calculateCardValueSum()

        // then
        assertThat(actual).isEqualTo(8)
    }

    @Test
    fun `플레이어가 소지한 카드의 합은 10이다`() {
        // given
        val user = User.create(
            "산군",
            listOf<Card>(
                Card.of(Shape.CLUBS, CardNumber.FIVE),
                Card.of(Shape.DIAMONDS, CardNumber.FIVE),
            ),
        )

        // when
        val actual = user.addScoreTenIfHasAce()

        // then
        assertThat(actual).isEqualTo(10)
    }
}
