package blackjack.model.playing.cardhand

import blackjack.model.CLOVER_ACE
import blackjack.model.HEART_ACE
import blackjack.model.HEART_FIVE
import blackjack.model.HEART_NINE
import blackjack.model.HEART_QUEEN
import blackjack.model.HEART_SEVEN
import blackjack.model.HEART_TEN
import blackjack.model.SPADE_ACE
import blackjack.model.SPADE_JACK
import blackjack.model.SPADE_SIX
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardHandTest {
    // 딜러나 플레이어 카드 패의 합을 구하는 동작은 완전히 같다.
    @Test
    fun `숫자 카드는 각 숫자에 해당하는 점수로 계산한다`() {
        val cardHand =
            CardHand(
                CLOVER_ACE,
                HEART_SEVEN,
                SPADE_SIX,
                HEART_QUEEN,
            )

        assertThat(cardHand.calculateScore()).isEqualTo(24)
    }

    @Test
    fun `카드 패의 ACE 가 없을 때 카드의 합을 계산한다`() {
        val cardHand =
            CardHand(
                HEART_SEVEN,
                SPADE_SIX,
                HEART_FIVE,
            )

        assertThat(cardHand.calculateScore()).isEqualTo(18)
    }

    @Test
    fun `A 카드가 포함되어 있으면 10점을 추가로 획득한다`() {
        val cardHand =
            CardHand(
                SPADE_ACE,
                HEART_ACE,
            )

        assertThat(cardHand.calculateScore()).isEqualTo(12)
    }

    @Test
    fun `카드 패의 ACE 가 세 장 있을 때 한 장만 11이 된다`() {
        val cardHand =
            CardHand(
                SPADE_ACE,
                HEART_ACE,
                CLOVER_ACE,
            )

        assertThat(cardHand.calculateScore()).isEqualTo(13)
    }

    @Test
    fun `A 카드가 포함되어 있을 때, 10점을 추가해서 21점을 초과한다면, 10점을 추가하지 않는다`() {
        val cardHand =
            mutableListOf(
                SPADE_ACE,
                HEART_NINE,
            )
        val currentCardHand = CardHand(cardHand + HEART_TEN)

        val actual = currentCardHand.calculateScore()
        assertThat(actual).isEqualTo(20)
    }

    @Test
    fun `J, Q, K 카드는 10점으로 계산한다`() {
        val hand =
            mutableListOf(
                SPADE_JACK,
                HEART_QUEEN,
            )
        assertThat(CardHand(hand).calculateScore()).isEqualTo(20)
    }
}
