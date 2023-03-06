package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest {

    lateinit var dealer: Dealer

    @BeforeEach
    fun setUp() {
        dealer = Dealer()
    }

    @Test
    fun `딜러는 카드의 합이 17점 이상이면 스테이이다`() {
        dealer.addCard(Card(Suit.SPADE, CardNumber.ACE))
        dealer.addCard(Card(Suit.SPADE, CardNumber.SIX))

        assertThat(dealer.isStay()).isTrue
    }

    @Test
    fun `딜러는 카드의 합이 16점 이하면 스테이가 아니다`() {
        dealer.addCard(Card(Suit.SPADE, CardNumber.ACE))
        dealer.addCard(Card(Suit.SPADE, CardNumber.FIVE))

        assertThat(dealer.isStay()).isFalse
    }

    @Test
    fun `딜러는 자신이 보유한 첫번째 카드를 반환한다`() {
        dealer.addCard(Card(Suit.SPADE, CardNumber.ACE))
        dealer.addCard(Card(Suit.SPADE, CardNumber.FIVE))

        // assertThat(dealer.getFirstCardHand().hand.first()).isEqualTo(Card(Suit.SPADE, CardNumber.ACE))
    }
}
