package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class PlayerCardHandTest {
    @Test
    fun `카드 패가 21 초과이면 BURST 패가 터진다`() {
        val playerCardHand =
            PlayerCardHand(
                Card(CardShape.SPADE, CardNumber.SIX),
                Card(CardShape.HEART, CardNumber.SEVEN),
                Card(CardShape.HEART, CardNumber.TEN),
            )

        val actual = playerCardHand.getCardHandState(true)

        assertThat(actual).isEqualTo(CardHandState.BURST)
    }

    @Test
    fun `카드 패가 21 이면 Black Jack 이 된다`() {
        val playerCardHand =
            PlayerCardHand(
                Card(CardShape.SPADE, CardNumber.SIX),
                Card(CardShape.HEART, CardNumber.SEVEN),
                Card(CardShape.HEART, CardNumber.EIGHT),
            )

        val actual = playerCardHand.getCardHandState(true)

        assertThat(actual).isEqualTo(CardHandState.BLACKJACK)
    }

    @ParameterizedTest
    @CsvSource("true, HIT", "false, STAY")
    fun `카드 패가 21 미만일 때, Player 의 결정에 따라 카드 패 상태를 구한다`(
        isHit: Boolean,
        state: String,
    ) {
        val playerCardHand =
            PlayerCardHand(
                Card(CardShape.SPADE, CardNumber.SIX),
                Card(CardShape.HEART, CardNumber.SEVEN),
            )

        val actual = playerCardHand.getCardHandState(isHit)

        assertThat(actual).isEqualTo(CardHandState.valueOf(state))
    }
}
