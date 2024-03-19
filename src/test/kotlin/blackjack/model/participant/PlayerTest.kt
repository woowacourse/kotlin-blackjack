package blackjack.model.participant

import blackjack.model.HEART_ACE
import blackjack.model.HEART_FIVE
import blackjack.model.HEART_KING
import blackjack.model.HEART_NINE
import blackjack.model.HEART_TEN
import blackjack.model.HEART_THREE
import blackjack.model.HEART_TWO
import blackjack.model.card.Card
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class PlayerTest {
    @MethodSource("카드 받을 수 있는지 여부 판단 테스트 데이터")
    @ParameterizedTest
    fun `카드를 더 받을 수 있는지 판단한다`(
        cards: List<Card>,
        expected: Boolean,
    ) {
        // given
        val player = Player(PlayerName("olive"), BattingAmount(1000))
        cards.forEach { player.receiveCard(it) }

        // when
        val actual = player.decideMoreCard()

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @MethodSource("카드 값 계산 테스트 데이터")
    @ParameterizedTest
    fun `플레이어의 카드 값을 구한다`(
        cards: List<Card>,
        expected: Int,
    ) {
        // given
        val player = Player(PlayerName("hi"), BattingAmount(1000))

        // when
        cards.forEach { player.receiveCard(it) }
        val actual = player.getCardSum()

        // then
        assertThat(actual).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun `카드 받을 수 있는지 여부 판단 테스트 데이터`() =
            listOf(
                Arguments.of(listOf(HEART_THREE, HEART_FIVE), true),
                Arguments.of(listOf(HEART_TEN, HEART_KING), true),
                Arguments.of(listOf(HEART_NINE, HEART_KING, HEART_TWO), false),
                Arguments.of(listOf(HEART_TEN, HEART_KING, HEART_THREE), false),
            )

        @JvmStatic
        fun `카드 값 계산 테스트 데이터`() =
            listOf(
                Arguments.of(listOf(HEART_THREE, HEART_FIVE), 8),
                Arguments.of(listOf(HEART_ACE), 11),
                Arguments.of(listOf(HEART_ACE, HEART_ACE), 12),
                Arguments.of(listOf(HEART_ACE, HEART_KING), 21),
            )
    }
}
