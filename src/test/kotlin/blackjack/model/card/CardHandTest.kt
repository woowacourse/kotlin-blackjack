package blackjack.model.card

import blackjack.model.result.Score
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class CardHandTest {
    // 딜러나 플레이어 카드 패의 합을 구하는 동작은 완전히 같다.
    @Test
    fun `카드 패에 ace 가 있고 숫자 합이 11 이하일 때 카드 패의 점수를 구한다`() {
        val cardHand =
            CardHand(
                Card(CardShape.CLOVER, CardNumber.ACE),
                Card(CardShape.CLOVER, CardNumber.QUEEN),
            )

        assertThat(cardHand.calculateScore()).isEqualTo(Score(21))
    }

    @Test
    fun `카드 패에 ace 가 있지만 숫자 합이 11 초과일 때 카드 패의 점수를 구한다`() {
        val cardHand =
            CardHand(
                Card(CardShape.CLOVER, CardNumber.ACE),
                Card(CardShape.HEART, CardNumber.SEVEN),
                Card(CardShape.DIAMOND, CardNumber.FIVE),
            )

        assertThat(cardHand.calculateScore()).isEqualTo(Score(13))
    }

    @Test
    fun `카드 패의 ACE 가 없을 때 카드 패의 점수를 계산한다`() {
        val cardHand =
            CardHand(
                Card(CardShape.SPADE, CardNumber.SEVEN),
                Card(CardShape.HEART, CardNumber.SIX),
                Card(CardShape.HEART, CardNumber.FIVE),
            )

        assertThat(cardHand.calculateScore()).isEqualTo(Score(18))
    }

    @Test
    fun `카드 손 패에 카드를 추가한다`() {
        val cardHand = CardHand()
        cardHand.addNewCard(Card(CardShape.SPADE, CardNumber.ACE))
        assertThat(cardHand).isEqualTo(CardHand(Card(CardShape.SPADE, CardNumber.ACE)))
    }

    @Test
    fun `카드 손 패에 두 장 미만의 카드가 있으면 초기 상태이다`() {
        val cardHand = CardHand(Card(CardShape.SPADE, CardNumber.SEVEN))
        assertTrue(cardHand.isReady())
    }
}
