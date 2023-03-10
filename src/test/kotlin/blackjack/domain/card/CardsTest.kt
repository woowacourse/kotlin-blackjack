package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {
    @Test
    fun `카드 목록에 카드를 한장 추가한다`() {
        val cards = Cards()
        cards.add(Card(CardNumber.ACE, Suit.SPADE))

        assertThat(cards.items.size).isEqualTo(1)
    }

    @Test
    fun `카드 목록에서 첫번째 카드를 반환한다`() {
        val cards = Cards(
            Card(CardNumber.ACE, Suit.SPADE),
            Card(CardNumber.THREE, Suit.SPADE),
            Card(CardNumber.FIVE, Suit.SPADE)
        )

        assertThat(cards.getFirstCard()).isEqualTo(Card(CardNumber.ACE, Suit.SPADE))
    }

    @Test
    fun `A는 기존 총 점수에 11점을 더한 값이 21점 이하이면 11점으로 계산한다`() {
        val cards = Cards(
            Card(CardNumber.ACE, Suit.SPADE)
        )

        assertThat(cards.calculateTotalScore()).isEqualTo(11)
    }

    @Test
    fun `A는 기존 총 점수에 11점을 더한 값이 21점을 초과하면 1점으로 계산한다`() {
        val cards = Cards(
            Card(CardNumber.ACE, Suit.SPADE),
            Card(CardNumber.ACE, Suit.HEART)
        )

        assertThat(cards.calculateTotalScore()).isEqualTo(12)
    }

    @Test
    fun `점수가 17점 이상이면 스테이다`() {
        val cards = Cards(
            Card(CardNumber.ACE, Suit.SPADE),
            Card(CardNumber.SIX, Suit.SPADE)
        )

        assertThat(cards.isStay()).isTrue
    }

    @Test
    fun `점수가 17점 미만이면 스테이가 아니다`() {
        val cards = Cards(
            Card(CardNumber.ACE, Suit.SPADE),
            Card(CardNumber.FIVE, Suit.SPADE)
        )

        assertThat(cards.isStay()).isFalse
    }

    @Test
    fun `점수가 21점을 초과하면 버스트다`() {
        val cards = Cards(
            Card(CardNumber.JACK, Suit.SPADE),
            Card(CardNumber.QUEEN, Suit.SPADE),
            Card(CardNumber.TWO, Suit.SPADE)
        )

        assertThat(cards.isOverBlackjack()).isTrue
    }

    @Test
    fun `점수가 21점을 초과하지 않으면 버스트가 아니다`() {
        val cards = Cards(
            Card(CardNumber.JACK, Suit.SPADE),
            Card(CardNumber.QUEEN, Suit.SPADE)
        )

        assertThat(cards.isOverBlackjack()).isFalse
    }
}
