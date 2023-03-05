package domain

import domain.card.Card
import domain.card.CardShape
import domain.card.CardValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class UserTest {
    @Test
    fun `플레이어는 소지한 카드의 합은 8이다`() {
        // given
        val user = User.create(
            "산군" to listOf<Card>(
                Card(CardShape.CLUBS, CardValue.FIVE),
                Card(CardShape.DIAMONDS, CardValue.THREE),
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
                    Card(CardShape.CLUBS, CardValue.FIVE),
                    Card(CardShape.DIAMONDS, CardValue.FIVE),
                ),
        )

        // when
        val actual = user.calculateCardValueSum()

        // then
        assertThat(actual).isEqualTo(10)
    }
}
