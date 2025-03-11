package blackjack

import blackjack.model.card.Card
import blackjack.model.participant.Dealer
import blackjack.model.card.CardNumber
import blackjack.model.card.Shape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest {
    private lateinit var dealer: Dealer

    @BeforeEach
    fun setUp() {
        dealer = Dealer()
    }

    @Test
    fun `딜러는 카드 총 합이 17 미만이면 카드를 받는다`() {
        dealer.addCard(Card(Shape.SPADE, CardNumber.SEVEN))
        dealer.addCard(Card(Shape.SPADE, CardNumber.TWO))
        val expect = true

        val actual = dealer.isMoreCard()

        assertThat(actual).isEqualTo(expect)
    }

    @Test
    fun `딜러는 카드 총 합이 17 이상이면 카드를 받지 않는다`() {
        dealer.addCard(Card(Shape.SPADE, CardNumber.SEVEN))
        dealer.addCard(Card(Shape.SPADE, CardNumber.TEN))
        val expect = false

        val actual = dealer.isMoreCard()

        assertThat(actual).isEqualTo(expect)
    }
}
