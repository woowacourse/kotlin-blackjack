package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class PlayerTest {
    @MethodSource("기준치 판단 테스트 데이터")
    @ParameterizedTest
    fun `카드의 합계가 기준치를 넘는지 판단한다`(
        cards: List<Card>,
        expected: Boolean,
    ) {
        // given
        val player = Player(PlayerName("olive"))
        cards.forEach { player.receiveCard(it) }

        // when
        val actual = player.isBurst()

        // then
        assertThat(actual).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun `기준치 판단 테스트 데이터`() =
            listOf(
                Arguments.of(listOf(Card("10", "하트"), Card("K", "다이아몬드")), false),
                Arguments.of(listOf(Card("10", "하트"), Card("K", "다이아몬드"), Card("1", "다이아몬드")), true),
                Arguments.of(listOf(Card("10", "하트"), Card("K", "다이아몬드"), Card("2", "다이아몬드")), true),
            )
    }
}
