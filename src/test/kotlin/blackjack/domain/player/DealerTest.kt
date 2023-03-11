package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import blackjack.domain.card.Cards
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러가 카드를 8클로버만 가지고 있을 때, 카드를 더 받아야 하는 상태인지 확인하면, true이다`() {

        // given
        val dealer = Dealer(cards = Cards(listOf(Card(CardNumber.EIGHT, CardShape.CLOVER))))

        // when
        val actual: Boolean = dealer.checkProvideCardPossible()

        // then
        assertThat(actual).isTrue
    }
}
