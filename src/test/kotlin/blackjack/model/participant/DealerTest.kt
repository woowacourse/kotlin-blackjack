package blackjack.model.participant

import blackjack.model.card.Card
import blackjack.model.card.TestCardProvider
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
        dealer.receiveCard(cards)

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
        dealer.receiveCard(cards)

        // when
        val actual = dealer.getCardSum()

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
        val expected = listOf(Card.of("K", "하트"), Card.of("K", "하트"))
        assertThat(dealer.getCards()).isEqualTo(expected)
    }

    @Test
    fun `게임의 최종 승패 결과를 계산한다`() {
        // given
        val dealer = Dealer()
        val players = Players.from(listOf("olive", "seogi", "chae"))

        dealer.receiveCard(listOf(Card.of("8", "하트")))
        players.playerGroup.forEachIndexed { idx, player ->
            player.receiveCard(listOf(Card.of(denominationValues[idx], "하트")))
        }

        // when
        val gameResultStorage = dealer.calculateGameResult(players)

        // then
        assertThat(gameResultStorage.dealerResult.results)
            .containsEntry(GameResultType.LOSE, 1)
            .containsEntry(GameResultType.WIN, 1)
            .containsEntry(GameResultType.DRAW, 1)
        assertThat(gameResultStorage.playersResult.results)
            .containsEntry(PlayerName("olive"), GameResultType.LOSE)
            .containsEntry(PlayerName("seogi"), GameResultType.WIN)
            .containsEntry(PlayerName("chae"), GameResultType.DRAW)
    }

    companion object {
        private val denominationValues = listOf("2", "K", "8")

        @JvmStatic
        fun `카드 받을 수 있는지 여부 판단 테스트 데이터`() =
            listOf(
                Arguments.of(listOf(Card.of("10", "하트"), Card.of("5", "다이아몬드")), true),
                Arguments.of(listOf(Card.of("10", "하트"), Card.of("5", "다이아몬드"), Card.of("A", "다이아몬드")), true),
                Arguments.of(listOf(Card.of("10", "하트"), Card.of("K", "다이아몬드")), false),
                Arguments.of(listOf(Card.of("10", "하트"), Card.of("7", "다이아몬드")), false),
            )

        @JvmStatic
        fun `카드 값 계산 테스트 데이터`() =
            listOf(
                Arguments.of(listOf(Card.of("5", "하트"), Card.of("3", "하트")), 8),
                Arguments.of(listOf(Card.of("A", "하트")), 11),
                Arguments.of(listOf(Card.of("A", "하트"), Card.of("A", "다이아몬드")), 12),
                Arguments.of(listOf(Card.of("A", "하트"), Card.of("6", "다이아몬드")), 17),
                Arguments.of(listOf(Card.of("A", "하트"), Card.of("7", "다이아몬드")), 18),
            )
    }
}
