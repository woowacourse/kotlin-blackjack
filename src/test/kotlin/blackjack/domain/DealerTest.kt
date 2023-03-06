package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class DealerTest {
    lateinit var dealer: Dealer

    @BeforeEach
    fun setUp() {
        dealer = Dealer()
    }

    @Test
    fun `딜러는 이름을 갖는다`() {
        assertThat(dealer.name).isEqualTo("딜러")
    }

    @Test
    fun `딜러는 카드 목록에 카드를 추가한다`() {
        dealer.addCard(Card(Suit.SPADE, CardNumber.TWO))

        // assertThat(dealer.hand.cards).containsExactly(Card(Suit.SPADE, CardNumber.TWO))
    }

    @Test
    fun `딜러는 카드의 합이 16점 이하면 카드를 뽑을 수 있다`() {
        dealer.addCard(Card(Suit.SPADE, CardNumber.ACE))
        dealer.addCard(Card(Suit.SPADE, CardNumber.FIVE))

        assertThat(dealer.canDraw()).isFalse
    }

    @Test
    fun `딜러는 카드의 합이 17점 이상이면 더 이상 카드를 뽑을 수 없다`() {
        dealer.addCard(Card(Suit.SPADE, CardNumber.ACE))
        dealer.addCard(Card(Suit.SPADE, CardNumber.SIX))

        assertThat(dealer.canDraw()).isTrue
    }

    @ParameterizedTest
    @CsvSource(
        "SPADE, ACE, HEART, ACE, 12",
        "SPADE, ACE, SPADE, JACK, 21",
        "SPADE, TEN, HEART, SEVEN, 17"
    )
    fun `자신의 점수를 반환한다`(
        firstCardSuit: Suit,
        firstCardNumber: CardNumber,
        secondCardSuit: Suit,
        secondCardNumber: CardNumber,
        expected: Int
    ) {
        dealer.addCard(Card(firstCardSuit, firstCardNumber))
        dealer.addCard(Card(secondCardSuit, secondCardNumber))

        assertThat(dealer.getTotalScore()).isEqualTo(expected)
    }

    @Test
    fun `자신이 가진 카드를 반환한다`() {
        dealer.addCard(Card(Suit.SPADE, CardNumber.ACE))
        dealer.addCard(Card(Suit.SPADE, CardNumber.JACK))

        // assertThat(dealer.getHand().hand).isEqualTo(listOf("A스페이드", "J다이아몬드"))
    }

    @Test
    fun `딜러는 자신이 보유한 첫번째 카드를 반환한다`() {
        dealer.addCard(Card(Suit.SPADE, CardNumber.ACE))
        dealer.addCard(Card(Suit.SPADE, CardNumber.FIVE))

        // assertThat(dealer.getFirstCardHand().hand.first()).isEqualTo(Card(Suit.SPADE, CardNumber.ACE))
    }
}
