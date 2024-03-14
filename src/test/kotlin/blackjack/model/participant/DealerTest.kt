package blackjack.model.participant

import blackjack.model.Card
import blackjack.model.card.Card
import blackjack.model.card.Denomination
import blackjack.model.card.Suite
import blackjack.model.result.GameResultType
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
    fun `게임의 최종 승패 결과를 계산한다`() {
        // given
        val dealer = Dealer()
        val players = Players.from(listOf("olive", "seogi"))

        dealer.receiveCard(Card.of(Denomination.KING, Suite.HEART))
        dealer.receiveCard(Card.of(Denomination.ACE, Suite.HEART))

        players.playerGroup[0].receiveCard(Card("K"))
        players.playerGroup[0].receiveCard(Card("A"))
        players.playerGroup[1].receiveCard(Card("8"))
        players.playerGroup[1].receiveCard(Card("8"))

        // when
        val actual = dealer.calculateGameResult(players)

        // then
        assertThat(actual.dealerResult.results)
            .containsEntry(GameResultType.WIN, 1)
            .containsEntry(GameResultType.DRAW, 1)

        assertThat(actual.playersResult.results)
            .containsEntry(PlayerName("olive"), GameResultType.DRAW)
            .containsEntry(PlayerName("seogi"), GameResultType.LOSE)
    }

    companion object {
        @JvmStatic
        fun `카드 받을 수 있는지 여부 판단 테스트 데이터`() =
            listOf(
                Arguments.of(listOf(Card("10"), Card("5")), true),
                Arguments.of(listOf(Card("10"), Card("5"), Card("A")), true),
                Arguments.of(listOf(Card("10"), Card("K")), false),
                Arguments.of(listOf(Card("10"), Card("7")), false),
            )

        @JvmStatic
        fun `카드 값 계산 테스트 데이터`() =
            listOf(
                Arguments.of(listOf(Card("5"), Card("3")), 8),
                Arguments.of(listOf(Card("A")), 11),
                Arguments.of(listOf(Card("A"), Card("A")), 12),
                Arguments.of(listOf(Card("A"), Card("6")), 17),
            )

        @JvmStatic
        fun `게임 결과 결정 테스트 데이터`() =
            listOf(
                Arguments.of(
                    listOf(Card("K"), Card("J"), Card("6")),
                    listOf(Card("K"), Card("Q"), Card("2")),
                    GameResultType.WIN,
                ),
                Arguments.of(
                    listOf(Card("K")),
                    listOf(Card("K"), Card("Q"), Card("2")),
                    GameResultType.WIN,
                ),
                Arguments.of(
                    listOf(Card("K"), Card("J"), Card("6")),
                    listOf(Card("K")),
                    GameResultType.LOSE,
                ),
                Arguments.of(listOf(Card("K")), listOf(Card("Q")), GameResultType.DRAW),
            )
    }
}
