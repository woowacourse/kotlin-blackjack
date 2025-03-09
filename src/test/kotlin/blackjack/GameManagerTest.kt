package blackjack

import blackjack.model.Dealer
import blackjack.model.DrawChoice
import blackjack.model.GameManager
import blackjack.model.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class GameManagerTest {
    @Test
    fun `초기 세팅은 플레이어와 딜러에게 카드 2장씩 나눠준다`() {
        // given
        val player1 = Player("a")
        val player2 = Player("b")
        val dealer = Dealer()
        val gameManager = GameManager(dealer, listOf(player1, player2))

        // when
        gameManager.dealInitialCardWithCount(2)

        // then
        assertThat(player1.cards.size).isEqualTo(2)
        assertThat(dealer.cards.size).isEqualTo(2)
    }

    @ParameterizedTest
    @MethodSource("distributeCard")
    fun `카드 추가 응답에 맞게 카드 추가 여부를 반환한다`(
        choice: DrawChoice,
        player: Player,
        expected: Boolean,
    ) {
        val gameManager = GameManager(Dealer(), listOf(player))

        val actual = gameManager.distributeCard(choice, player)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `카드 추가 요청을 한다면 플레이어 카드는 한 장 추가된다`() {
        val player = Player("플레이어")
        val gameManager = GameManager(Dealer(), listOf(player))
        val expected = player.cards.size + 1

        gameManager.distributeCard(DrawChoice.YES, player)

        val actual = player.cards.size

        assertThat(actual).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun distributeCard(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(DrawChoice.YES, Player("플레이어"), true),
                Arguments.of(DrawChoice.NO, Player("플레이어"), false),
            )
        }
    }
}
