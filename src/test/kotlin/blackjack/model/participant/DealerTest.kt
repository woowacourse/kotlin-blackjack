package blackjack.model.participant

import blackjack.model.HEART_ACE
import blackjack.model.HEART_EIGHT
import blackjack.model.HEART_FIVE
import blackjack.model.HEART_KING
import blackjack.model.HEART_QUEEN
import blackjack.model.HEART_SEVEN
import blackjack.model.HEART_SIX
import blackjack.model.HEART_TEN
import blackjack.model.HEART_THREE
import blackjack.model.Player
import blackjack.model.card.Card
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class DealerTest {
    @MethodSource("카드 받을 수 있는지 여부 판단 테스트 데이터")
    @ParameterizedTest
    fun `카드를 더 받을 수 있는지 판단한다`(
        cards: List<Card>,
        expected: Boolean,
    ) {
        // given
        val dealer = Dealer()
        cards.forEach { dealer.receiveCard(it) }

        // when
        val actual = dealer.decideMoreCard()

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @MethodSource("카드 값 계산 테스트 데이터")
    @ParameterizedTest
    fun `딜러의 카드 값을 구한다`(
        cards: List<Card>,
        expected: Int,
    ) {
        // given
        val dealer = Dealer()
        cards.forEach { dealer.receiveCard(it) }

        // when
        val actual = dealer.getCardSum()

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `게임의 최종 수익 결과를 계산한다`() {
        // given
        val dealer =
            Dealer().apply {
                this.receiveCard(HEART_KING)
                this.receiveCard(HEART_QUEEN)
            }
        val player1 =
            Player("olive").apply {
                this.receiveCard(HEART_KING)
                this.receiveCard(HEART_ACE)
            }
        val player2 =
            Player("seogi").apply {
                this.receiveCard(HEART_EIGHT)
                this.receiveCard(HEART_EIGHT)
            }
        val players = Players(listOf(player1, player2))

        // when
        val actual = dealer.calculateGameResult(players)

        // then
        assertThat(actual.dealerResult.profit).isEqualTo(Profit(-500.0))

        assertThat(actual.playersResult.results)
            .containsEntry(PlayerName("olive"), Profit(1500.0))
            .containsEntry(PlayerName("seogi"), Profit(-1000.0))
    }

    companion object {
        @JvmStatic
        fun `카드 받을 수 있는지 여부 판단 테스트 데이터`() =
            listOf(
                Arguments.of(listOf(HEART_TEN, HEART_FIVE), true),
                Arguments.of(listOf(HEART_TEN, HEART_FIVE, HEART_ACE), true),
                Arguments.of(listOf(HEART_TEN, HEART_KING), false),
                Arguments.of(listOf(HEART_TEN, HEART_SEVEN), false),
            )

        @JvmStatic
        fun `카드 값 계산 테스트 데이터`() =
            listOf(
                Arguments.of(listOf(HEART_FIVE, HEART_THREE), 8),
                Arguments.of(listOf(HEART_ACE), 11),
                Arguments.of(listOf(HEART_ACE, HEART_ACE), 12),
                Arguments.of(listOf(HEART_ACE, HEART_SIX), 17),
            )
    }
}
