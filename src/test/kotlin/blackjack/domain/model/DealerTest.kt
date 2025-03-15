package blackjack.domain.model

import blackjack.domain.model.card.Card
import blackjack.domain.model.card.Rank
import blackjack.domain.model.card.Suit
import blackjack.domain.model.participant.Dealer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러의 점수가 16 이하면 히트할 수 있다`() {
        val dealer = Dealer(Card(Suit.HEART, Rank.SIX), Card(Suit.HEART, Rank.KING))
        assertThat(dealer.canHit()).isTrue()
    }

    @Test
    fun `딜러의 점수가 16 초과면 히트할 수 없다`() {
        val dealer = Dealer(Card(Suit.HEART, Rank.QUEEN), Card(Suit.HEART, Rank.KING))
        assertThat(dealer.canHit()).isFalse()
    }

    @Test
    fun `딜러는 최초로 패를 공개할 때 첫 번째 카드만 공개한다`() {
        val dealer = Dealer(Card(Suit.HEART, Rank.SIX), Card(Suit.HEART, Rank.KING))
        assertThat(dealer.showHand()).isEqualTo(listOf(Card(Suit.HEART, Rank.SIX)))
    }

    @Test
    fun `딜러는 마지막으로 패를 공개할 때 모든 카드를 공개한다`() {
        val dealer = Dealer(Card(Suit.HEART, Rank.SIX), Card(Suit.HEART, Rank.KING))
        dealer.showHand()
        assertThat(dealer.showHand()).isEqualTo(listOf(Card(Suit.HEART, Rank.SIX), Card(Suit.HEART, Rank.KING)))
    }
}
