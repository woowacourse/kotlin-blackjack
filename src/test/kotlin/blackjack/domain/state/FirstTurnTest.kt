package blackjack.domain.state

import blackjack.domain.CardBunch
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FirstTurnTest {
    @Test
    fun `FirstTurn 에서 카드를 뽑았을때 BlackJack이라면 BlackJack 객체를 반환한다`() {
        val firstTurn = FirstTurn(CardBunch(Cards.ace))
        assertThat(firstTurn.draw(Cards.king)).isInstanceOf(BlackJack::class.java)
    }

    @Test
    fun `FirstTurn 에서 카드를 뽑았을때 BlackJack이 아니라면 Hit객체를 반환한다`() {
        val firstTurn = FirstTurn(CardBunch(Cards.ace))
        assertThat(firstTurn.draw(Cards.two)).isInstanceOf(Hit::class.java)
    }
}
