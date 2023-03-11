package domain

import domain.card.Card
import domain.card.CardShape
import domain.card.CardValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class UserTest {
    @Test
    fun `이름이 해시, 배팅금액이 1000, 카드 2장을 가진 유저를 생성한다`() {
        val user = User.create(
            UserBetAmount("해시", 1000),
            Cards(
                listOf(
                    Card(CardShape.DIAMONDS, CardValue.ACE),
                    Card(CardShape.HEARTS, CardValue.TWO)
                )
            )
        )
        assertThat(user.name).isEqualTo("해시")
        assertThat(user.getCards().size).isEqualTo(2)
        assertThat(user.betAmount).isEqualTo(1000.0)
    }
}
