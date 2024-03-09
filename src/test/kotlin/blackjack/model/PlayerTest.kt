package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class PlayerTest {
    @Test
    fun `플레이어 카드의 총합을 계산한다`() {
        val player =
            Player(
                gameInfo = GameInfo(
                    "해음",
                    setOf(
                        Card(Shape.CLOVER, CardValue.SIX),
                        Card(Shape.HEART, CardValue.K),
                    ),
                ),
            ) { "y" }

        assertThat(player.gameInfo.sumCardValues()).isEqualTo(16)
    }

    @Test
    fun `플레이어가 카드를 더 받는다고 응답하면, 보유 카드에 추가한다`() {
        val player =
            Player(
                gameInfo =
                GameInfo(
                    "해음",
                    setOf(
                        Card(Shape.CLOVER, CardValue.SIX),
                        Card(Shape.HEART, CardValue.K),
                    ),
                ),
            ) { "y" }

        player.drawCard {
            Card(Shape.DIAMOND, CardValue.SEVEN)
        }
        assertThat(player.gameInfo.cards).hasSize(3)
    }

    @Test
    fun `플레이어가 카드를 더 받지 않겠다고 응답하면, 현재 보유 카드에 변경이 없도록 한다`() {
        val cards = setOf(Card(Shape.HEART, CardValue.SIX))
        val player = Player(GameInfo("haeum", cards)) { "n" }
        val actualState =
            player.drawCard {
                Card(Shape.DIAMOND, CardValue.SEVEN)
            }
        assertAll(
            { assertThat(player.gameInfo.cards).hasSize(1) },
            { assertThat(actualState).isEqualTo(PickingState.STAND) },
        )
    }

    @Test
    fun `플레이어가 카드를 뽑았을 때, 최대 가능 총합을 초과하면, 입력 종료를 의미하는 클래스를 반환한다`() {
        val cards =
            setOf(
                Card(Shape.HEART, CardValue.TEN),
                Card(Shape.DIAMOND, CardValue.TEN),
            )
        val player = Player(GameInfo("haeum", cards)) { "y" }
        val actualState =
            player.drawCard {
                Card(Shape.DIAMOND, CardValue.TWO)
            }
        assertAll(
            { assertThat(player.gameInfo.cards).hasSize(3) },
            { assertThat(actualState).isEqualTo(PickingState.STAND) },
        )
    }
}
