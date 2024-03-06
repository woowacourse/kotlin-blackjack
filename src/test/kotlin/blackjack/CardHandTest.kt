package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardHandTest {
    // 딜러나 플레이어 카드 패의 합을 구하는 동작은 완전히 같다.
    @Test
    fun `카드 핸드의 총 합을 구한다`() {
        val cardHand =
            CardHand(
                Card(CardShape.CLOVER, CardNumber.ACE),
                Card(CardShape.HEART, CardNumber.SEVEN),
                Card(CardShape.SPADE, CardNumber.SIX),
                Card(CardShape.CLOVER, CardNumber.QUEEN),
            )
        val actual = cardHand.sum()
        assertThat(actual).isEqualTo(24)
    }

    @Test
    fun `카드 패의 ACE 가 없을 때 카드의 합을 계산한다`() {
        val cardHand =
            CardHand(
                Card(CardShape.SPADE, CardNumber.SEVEN),
                Card(CardShape.HEART, CardNumber.SIX),
                Card(CardShape.HEART, CardNumber.FIVE),
            )
        val actual = cardHand.sum()
        assertThat(actual).isEqualTo(18)
    }

    @Test
    fun `카드 패의 ACE 가 두 장 있을 때 한 장의 값은 1이 된다`() {
        val cardHand =
            CardHand(
                Card(CardShape.SPADE, CardNumber.ACE),
                Card(CardShape.HEART, CardNumber.ACE),
            )
        val actual = cardHand.sum()
        assertThat(actual).isEqualTo(12)
    }

    @Test
    fun `카드 패의 ACE 가 세 장 있을 때 두 장의 값은 각각 1이 된다`() {
        val cardHand =
            CardHand(
                Card(CardShape.SPADE, CardNumber.ACE),
                Card(CardShape.HEART, CardNumber.ACE),
                Card(CardShape.HEART, CardNumber.ACE),
            )
        val actual = cardHand.sum()
        assertThat(actual).isEqualTo(13)
    }

    @Test
    fun `카드 패의 ACE 가 두 장 있는 상태에서(이 때는 한 장이 1) TEN 이 추가되면, 남은 ACE 한 장을 1로 변경한다`() {
        val cardHand =
            mutableListOf(
                Card(CardShape.SPADE, CardNumber.ACE),
                Card(CardShape.HEART, CardNumber.ACE),
            )

        cardHand.add(Card(CardShape.HEART, CardNumber.TEN))
        val currentCardHand = CardHand(cardHand)

        val actual = currentCardHand.sum()
        assertThat(actual).isEqualTo(12)
    }
}
