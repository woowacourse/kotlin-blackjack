package blackjack.domain.model

import blackjack.domain.CLUB_KING
import blackjack.domain.HEART_ACE
import blackjack.domain.HEART_KING
import blackjack.domain.HEART_SIX
import blackjack.domain.model.hand.Hands
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class HandsTest {
    private lateinit var hands: Hands

    @BeforeEach
    fun setUp() {
        hands = Hands(HEART_ACE, HEART_KING)
    }

    @Test
    fun `지정한 1개의 카드만 반환한다`() {
        assertThat(hands.extractCards(1)).isEqualTo(listOf(HEART_ACE))
    }

    @Test
    fun `카드를 추가해서 새로 만든다`() {
        val actual = listOf(HEART_ACE, HEART_KING, HEART_SIX)
        val nextHands = hands.nextHand(HEART_SIX)
        assertThat(nextHands.cards).isEqualTo(actual)
    }

    @Test
    fun `카드를 더한 값이 11이하이고 에이스가 포함될 경우 10점 보너스를 받는다`() {
        hands = Hands(HEART_ACE, HEART_KING) // 1+10 보너스 10
        assertThat(hands.score()).isEqualTo(21)
    }

    @Test
    fun `카드를 더한 값이 11이상일 경우 에이스가 포함되어도 보너스 값을 받지 않는다`() {
        hands = Hands(HEART_ACE, HEART_KING, CLUB_KING) // 1 +10 +10
        assertThat(hands.score()).isEqualTo(21)
    }
}
