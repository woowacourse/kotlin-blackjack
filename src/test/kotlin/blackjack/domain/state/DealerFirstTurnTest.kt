package blackjack.domain.state

import blackjack.domain.CardBunch
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class DealerFirstTurnTest {
    @Test
    fun `DealersFirstTurn 에서 카드를 뽑았을때 BlackJack이라면 BlackJack 객체를 반환한다`() {
        val firstTurn = DealerFirstTurn(CardBunch(Cards.ace))
        Assertions.assertThat(firstTurn.draw(Cards.king)).isInstanceOf(BlackJack::class.java)
    }

    @Test
    fun `DealerFirstTurn 에서 카드를 뽑았을때 BlackJack이 아니라면 DealerHit객체를 반환한다`() {
        val firstTurn = DealerFirstTurn(CardBunch(Cards.ace))
        Assertions.assertThat(firstTurn.draw(Cards.two)).isInstanceOf(DealerHit::class.java)
    }
}
