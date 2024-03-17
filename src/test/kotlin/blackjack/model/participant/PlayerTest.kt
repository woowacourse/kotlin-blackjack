package blackjack.model.participant

import blackjack.model.DEFAULT_BATTING_AMOUNT
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

private fun Player(
    amount: Int = DEFAULT_BATTING_AMOUNT,
    cards: List<Card> = listOf(),
): Player {
    return Player("olive", amount, cards)
}

class PlayerTest {
    @Test
    fun `카드의 점수가 21 미만이면 카드를 더 받을 수 있다`() {
        // given
        val player = Player(cards = listOf(HEART_THREE, SPADE_FIVE))

        // when
        val actual = player.receivableMoreCard()

        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun `카드의 점수가 21 이상이면 카드를 더 받을 수 없다`() {
        // given
        val player = Player(cards = listOf(SPADE_TEN, HEART_KING, HEART_THREE))

        // when
        val actual = player.receivableMoreCard()

        // then
        assertThat(actual).isFalse()
    }

    @Test
    fun `게임 시작 시 플레이어가 2장의 카드를 받는다`() {
        // given
        val player = Player()

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
        val player = Player(cards = playerCards)

        // when
        val actual = player.profit(dealer)

        // then
        assertThat(actual.price).isEqualTo(expected)
    }

    companion object {
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
