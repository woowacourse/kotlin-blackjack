package domain

import domain.card.Card
import domain.card.CardShape
import domain.card.CardValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class UserTest {
    @Test
    fun `이름이 해시, 카드 2장을 가진 유저를 생성한다`() {
        val user = User.create(
            "해시" to
                listOf(
                    Card(CardShape.DIAMONDS, CardValue.ACE),
                    Card(CardShape.HEARTS, CardValue.TWO)
                )
        )
        assertThat(user.name).isEqualTo("해시")
        assertThat(user.cards.value.size).isEqualTo(2)
    }
}
