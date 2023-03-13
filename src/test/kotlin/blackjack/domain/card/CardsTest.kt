package blackjack.domain.card

import blackjack.domain.DIAMOND_ACE
import blackjack.domain.SPADE_ACE
import blackjack.domain.SPADE_FIVE
import blackjack.domain.SPADE_JACK
import blackjack.domain.SPADE_KING
import blackjack.domain.SPADE_THREE
import blackjack.domain.SPADE_TWO
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {
    @Test
    fun `카드 목록에 카드를 한장 추가한다`() {
        val cards = Cards().add(SPADE_ACE)

        assertThat(cards.items.size).isEqualTo(1)
    }

    @Test
    fun `카드 목록에서 첫번째 카드를 반환한다`() {
        val cards = Cards(
            SPADE_ACE,
            SPADE_THREE,
            SPADE_FIVE,
        )

        assertThat(cards.getFirstCard()).isEqualTo(SPADE_ACE)
    }

    @Test
    fun `A는 기존 총 점수에 11점을 더한 값이 21점 이하이면 11점으로 계산한다`() {
        val cards = Cards(
            SPADE_ACE,
        )

        assertThat(cards.calculateTotalScore()).isEqualTo(11)
    }

    @Test
    fun `A는 기존 총 점수에 11점을 더한 값이 21점을 초과하면 1점으로 계산한다`() {
        val cards = Cards(
            SPADE_ACE,
            DIAMOND_ACE,
        )

        assertThat(cards.calculateTotalScore()).isEqualTo(12)
    }

    @Test
    fun `점수가 21점을 초과하면 버스트다`() {
        val cards = Cards(
            SPADE_JACK,
            SPADE_KING,
            SPADE_TWO,
        )

        assertThat(cards.isBust).isTrue
    }

    @Test
    fun `점수가 21점을 초과하지 않으면 버스트가 아니다`() {
        val cards = Cards(
            SPADE_JACK,
            SPADE_KING,
        )

        assertThat(cards.isBust).isFalse
    }
}
