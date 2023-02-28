package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class UserTest {
    @Test
    fun `카드를 뽑을 수 있다`() {
        val user = User("아크")
        user.draw(Card(CardMark.CLOVER, CardValue.EIGHT))
        assertThat(user.cards.size).isEqualTo(1)
    }

    @Test
    fun `점수의 합을 반환한다`() {
        val user = User("아크")
        user.draw(Card(CardMark.CLOVER, CardValue.EIGHT))
        user.draw(Card(CardMark.SPADE, CardValue.EIGHT))
        assertThat(user.score).isEqualTo(16)
    }
}

/*
User가 할일은?
카드를 가지고 있고
점수를 가지고 있고
더 갈지 안갈지 입력받고 처리한다.

 */
