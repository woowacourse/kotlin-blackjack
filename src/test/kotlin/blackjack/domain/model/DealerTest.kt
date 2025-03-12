package blackjack.domain.model

import blackjack.domain.model.card.Card
import blackjack.domain.model.card.CardNumber
import blackjack.domain.model.card.Shape
import blackjack.domain.model.participant.Dealer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest {
    private val dealer = Dealer()

    @BeforeEach
    fun setup() {
        // given
        dealer.receiveCard(Card(Shape.Heart, CardNumber.Ace))
        dealer.receiveCard(Card(Shape.Spade, CardNumber.Six))
    }

    @Test
    fun `받은 카드의 목록을 반환한다`() {
        // when
        val actual = dealer.cardDeck
        val expected = listOf(Card(Shape.Heart, CardNumber.Ace), Card(Shape.Spade, CardNumber.Six))
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `카드 숫자 합이 임계값보다 작은지 판단한다`() {
        // when
        val actual = dealer.canHit()
        // then
        assertThat(actual).isFalse()
    }
}
