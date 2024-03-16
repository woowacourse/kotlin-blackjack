package blackjack.model

import blackjack.model.card.Card
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class HandCardsTest {
    @MethodSource("카드 점수 계산 테스트 데이터")
    @ParameterizedTest
    fun `카드 점수를 구한다`(
        cards: List<Card>,
        expected: Int,
    ) {
        // given
        val handCards = HandCards(cards.toMutableList())

        // when
        val actual = handCards.score(BURST_CONDITION)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    companion object {
        private const val BURST_CONDITION = 21

        @JvmStatic
        fun `카드 점수 계산 테스트 데이터`() =
            listOf(
                Arguments.of(listOf(SPADE_FIVE, HEART_THREE), 8),
                Arguments.of(listOf(SPADE_ACE), 11),
                Arguments.of(listOf(SPADE_ACE, SPADE_ACE), 12),
                Arguments.of(listOf(SPADE_ACE, HEART_KING), 21),
            )
    }
}
