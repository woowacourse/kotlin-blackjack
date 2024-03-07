package blackjack.model

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
                Arguments.of(listOf(Card.of("3", "하트"), Card.of("5", "다이아몬드")), true),
                Arguments.of(listOf(Card.of("10", "하트"), Card.of("K", "다이아몬드")), true),
                Arguments.of(listOf(Card.of("9", "하트"), Card.of("K", "다이아몬드"), Card.of("2", "다이아몬드")), false),
                Arguments.of(listOf(Card.of("10", "하트"), Card.of("K", "다이아몬드"), Card.of("3", "다이아몬드")), false),
            )

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
