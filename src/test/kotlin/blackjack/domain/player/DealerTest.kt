package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import blackjack.domain.card.Cards
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {

    @Test
    fun `갖고 있는 카드 숫자의 합이 16이 넘지 않는다면 카드를 더 받을 수 있는 상태이다`() {
        val dealer = Dealer("aaa")
        dealer.addCard(Card(CardNumber.EIGHT, CardShape.CLOVER))
        val actual = dealer.canHit()
        assertThat(actual).isEqualTo(true)
    }

    @Test
    fun `갖고 있는 카드 숫자의 합이 16이 넘는다면 카드를 더 받을 수 없는 상태이다`() {
        val cards: Cards = Cards(
            listOf(
                Card(CardNumber.KING, CardShape.CLOVER),
                Card(CardNumber.QUEEN, CardShape.CLOVER)
            )
        )
        val dealer = Dealer("aaa", cards)
        val actual = dealer.canHit()
        assertThat(actual).isEqualTo(false)
    }
}
