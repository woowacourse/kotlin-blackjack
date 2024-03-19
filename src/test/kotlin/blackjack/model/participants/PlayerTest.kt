package blackjack.model.participants

import blackjack.model.card.Card
import blackjack.model.card.CardValue
import blackjack.model.card.Shape
import blackjack.model.gameInfo.GameInfo
import blackjack.model.gameInfo.PickingState
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class PlayerTest {
    @Test
    fun `플레이어 카드의 총합을 계산한다`() {
        val player =
            Player(
                gameInfo =
                    GameInfo(
                        "해음",
                        cards =
                            setOf(
                                Card.of(Shape.CLOVER, CardValue.SIX, 0),
                                Card.of(Shape.HEART, CardValue.K, 6),
                            ),
                    ),
            ) { "y" }

        assertThat(player.gameInfo.sumOfCards).isEqualTo(16)
    }

    @Test
    fun `플레이어가 카드를 더 받는다고 응답하면, 보유 카드에 추가한다`() {
        val player =
            Player(
                gameInfo =
                    GameInfo(
                        "해음",
                        cards =
                            setOf(
                                Card.of(Shape.CLOVER, CardValue.SIX, 0),
                                Card.of(Shape.HEART, CardValue.K, 6),
                            ),
                    ),
            ) { "y" }

        player.drawSingleCard {
            Card(Shape.DIAMOND, CardValue.SEVEN.title, CardValue.SEVEN.value)
        }
        assertThat(player.gameInfo.cards).hasSize(3)
    }

    @Test
    fun `플레이어가 카드를 더 받지 않겠다고 응답하면, 현재 보유 카드에 변경이 없도록 한다`() {
        val cards = setOf(Card.of(Shape.HEART, CardValue.SIX, 0))
        val player = Player(GameInfo("haeum", cards = cards)) { "n" }
        val actualState =
            player.drawSingleCard {
                Card(Shape.DIAMOND, CardValue.SEVEN.title, CardValue.SEVEN.value)
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
                Card.of(Shape.HEART, CardValue.TEN, 0),
                Card.of(Shape.DIAMOND, CardValue.TEN, 10),
            )
        val player = Player(GameInfo("haeum", cards = cards)) { "y" }
        val actualState =
            player.drawSingleCard {
                Card(Shape.DIAMOND, CardValue.TWO.title, CardValue.TWO.value)
            }
        assertAll(
            { assertThat(player.gameInfo.cards).hasSize(3) },
            { assertThat(actualState).isEqualTo(PickingState.STAND) },
        )
    }
}
