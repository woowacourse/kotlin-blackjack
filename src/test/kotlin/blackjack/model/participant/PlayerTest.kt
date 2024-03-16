package blackjack.model.participant

import blackjack.model.DIAMOND_NINE
import blackjack.model.DIAMOND_TWO
import blackjack.model.HEART_KING
import blackjack.model.HEART_THREE
import blackjack.model.SPADE_ACE
import blackjack.model.SPADE_FIVE
import blackjack.model.SPADE_TEN
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
        val actual = player.receivableMoreCard()

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
        val expected = listOf(HEART_KING, HEART_KING)
        assertThat(player.cards()).isEqualTo(expected)
    }

    @MethodSource("수익 계산 테스트 데이터")
    @ParameterizedTest
    fun `수익을 계산한다`(
        dealerCards: List<Card>,
        playerCards: List<Card>,
        expected: Int,
    ) {
        // given
        val dealer = Dealer(dealerCards)
        val player = Player("olive", 1000, playerCards)

        // when
        val actual = player.profit(dealer)

        // then
        assertThat(actual.price).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun `카드 받을 수 있는지 여부 판단 테스트 데이터`() =
            listOf(
                Arguments.of(listOf(HEART_THREE, SPADE_FIVE), true),
                Arguments.of(listOf(SPADE_TEN, SPADE_TEN), true),
                Arguments.of(listOf(DIAMOND_NINE, HEART_KING, DIAMOND_TWO), false),
                Arguments.of(listOf(SPADE_TEN, HEART_KING, HEART_THREE), false),
            )

        @JvmStatic
        fun `수익 계산 테스트 데이터`() =
            listOf(
                Arguments.of(listOf(HEART_THREE), listOf(SPADE_TEN, SPADE_ACE), 1500),
                Arguments.of(listOf(HEART_THREE), listOf(SPADE_TEN, SPADE_TEN, SPADE_TEN), -1000),
                Arguments.of(listOf(SPADE_TEN, SPADE_TEN, SPADE_TEN), listOf(HEART_THREE), 1000),
                Arguments.of(listOf(HEART_THREE), listOf(SPADE_TEN), 1000),
                Arguments.of(listOf(HEART_THREE), listOf(HEART_THREE), 0),
                Arguments.of(listOf(SPADE_TEN), listOf(HEART_THREE), -1000),
            )
    }
}
