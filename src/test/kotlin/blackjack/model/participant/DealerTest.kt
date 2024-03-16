package blackjack.model.participant

import blackjack.model.DIAMOND_TWO
import blackjack.model.HEART_KING
import blackjack.model.HEART_SEVEN
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

class DealerTest {
    @MethodSource("카드 받을 수 있는지 여부 판단 테스트 데이터")
    @ParameterizedTest
    fun `카드를 더 받을 수 있는지 판단한다`(
        cards: List<Card>,
        expected: Boolean,
    ) {
        // given
        val dealer = Dealer(cards)

        // when
        val actual = dealer.receivableMoreCard()

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `게임 시작 시 딜러가 2장의 카드를 받는다`() {
        // given
        val dealer = Dealer()

        // when
        dealer.initCard(TestCardProvider)

        // then
        val expected = listOf(HEART_KING, HEART_KING)
        assertThat(dealer.cards()).isEqualTo(expected)
    }

    @Test
    fun `수익을 계산한다`() {
        // given
        val dealer = Dealer(listOf(DIAMOND_TWO))
        val players =
            Players.from(
                listOf("abc", "def"),
                listOf(1000, 3000),
            )

        // when
        players.playerGroup[0].receiveCard(listOf(SPADE_TEN))
        players.playerGroup[1].receiveCard(listOf(SPADE_ACE, HEART_KING))
        val actual = dealer.profit(players)

        // then
        assertThat(actual.price).isEqualTo(-5500)
    }

    companion object {
        @JvmStatic
        fun `카드 받을 수 있는지 여부 판단 테스트 데이터`() =
            listOf(
                Arguments.of(listOf(SPADE_TEN, SPADE_FIVE), true),
                Arguments.of(listOf(SPADE_TEN, SPADE_FIVE, SPADE_ACE), true),
                Arguments.of(listOf(SPADE_TEN, HEART_KING), false),
                Arguments.of(listOf(SPADE_TEN, HEART_SEVEN), false),
            )
    }
}
