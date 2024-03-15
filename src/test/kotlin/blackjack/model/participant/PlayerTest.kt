package blackjack.model.participant

import blackjack.model.card.Card
import blackjack.model.card.TestCardProvider
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
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
        val player = Player("olive", 1000)
        player.receiveCard(cards)

        // when
        val actual = player.decideMoreCard()

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `게임 시작 시 플레이어가 2장의 카드를 받는다`() {
        // given
        val player = Player("olive", 1000)

        // when
        player.initCard(TestCardProvider)

        // then
        val expected = listOf(Card.of("K", "하트"), Card.of("K", "하트"))
        assertThat(player.getCards()).isEqualTo(expected)
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
    }
}
