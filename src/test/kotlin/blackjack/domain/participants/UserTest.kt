package blackjack.domain.participants

import blackjack.domain.card.Card
import blackjack.domain.card.CardMark
import blackjack.domain.card.CardValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class UserTest {
    @Test
    fun `카드를 뽑을 수 있다`() {
        val user = Guest("아크")
        user.draw(Card(CardMark.CLOVER, CardValue.EIGHT))
        assertThat(user.cards.size).isEqualTo(1)
    }

    @Test
    fun `점수의 합을 반환한다`() {
        val user = Guest("아크")
        user.draw(Card(CardMark.CLOVER, CardValue.EIGHT))
        user.draw(Card(CardMark.SPADE, CardValue.EIGHT))
        assertThat(user.score()).isEqualTo(16)
    }
}
