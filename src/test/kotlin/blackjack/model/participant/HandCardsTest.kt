package blackjack.model.participant

import blackjack.model.card.Card
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class HandCardsTest {
    @MethodSource("카드 값 계산 테스트 데이터")
    @ParameterizedTest
    fun `플레이어의 카드 값을 구한다`(
        cards: List<Card>,
        expected: Int,
    ) {
        // given
        val handCards = HandCards().also { it.add(cards) }

        // when
        val actual = handCards.getCardSum(BURST_CONDITION)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    companion object {
        private const val BURST_CONDITION = 21

        @JvmStatic
        fun `카드 값 계산 테스트 데이터`() =
            listOf(
                Arguments.of(listOf(Card.of("5", "하트"), Card.of("3", "하트")), 8),
                Arguments.of(listOf(Card.of("A", "하트")), 11),
                Arguments.of(listOf(Card.of("A", "하트"), Card.of("A", "다이아몬드")), 12),
                Arguments.of(listOf(Card.of("A", "하트"), Card.of("K", "다이아몬드")), 21),
            )
    }
}
