package domain.participants

import domain.card.Card
import domain.card.CardValue
import domain.card.Cards
import domain.card.Shape
import domain.result.Result
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어의 결과를 반환한다`() {
        val cards = Cards(mutableListOf(Card(Shape.SPADE, CardValue.JACK)))
        val dealer = Dealer(cards)

        val result = Player("pingu", cards, Money(100)).getWinningResult(dealer)

        assertThat(result).isEqualTo(Result.DRAW)
    }
}
