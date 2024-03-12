package blackjack.model.participant

import blackjack.model.Card
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
        val player = Player(PlayerName("olive"))
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
        val player = Player(PlayerName("hi"))

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
                Arguments.of(listOf(Card("3"), Card("5")), true),
                Arguments.of(listOf(Card("10"), Card("K")), true),
                Arguments.of(listOf(Card("9"), Card("K"), Card("2")), false),
                Arguments.of(listOf(Card("10"), Card("K"), Card("3")), false),
            )

        @JvmStatic
        fun `카드 값 계산 테스트 데이터`() =
            listOf(
                Arguments.of(listOf(Card("5"), Card("3")), 8),
                Arguments.of(listOf(Card("A")), 11),
                Arguments.of(listOf(Card("A"), Card("A")), 12),
                Arguments.of(listOf(Card("A"), Card("K")), 21),
            )
    }
}
