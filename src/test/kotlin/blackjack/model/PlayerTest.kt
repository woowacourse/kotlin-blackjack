package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class PlayerTest {
    @Test
    fun `플레이어가 카드를 더 받는다고 응답하면, 보유 카드에 추가한다`() {
        val cards = setOf(Card.of(Shape.HEART, CardValue.SIX, 0))
        val player = Player("haeum", cards) { "y" }
        val actualState =
            player.getCard {
                Card(Shape.DIAMOND.title, CardValue.SEVEN.title, CardValue.SEVEN.value)
            }
        assertAll(
            { assertThat(player.cards).hasSize(2) },
            { assertThat(actualState).isEqualTo(PickingState.CONTINUE) },
        )
    }

    @Test
    fun `플레이어가 카드를 더 받지 않겠다고 응답하면, 현재 보유 카드에 변경이 없도록 한다`() {
        val cards = setOf(Card.of(Shape.HEART, CardValue.SIX, 0))
        val player = Player("haeum", cards) { "n" }
        val actualState =
            player.getCard {
                Card(Shape.DIAMOND.title, CardValue.SEVEN.title, CardValue.SEVEN.value)
            }
        assertAll(
            { assertThat(player.cards).hasSize(1) },
            { assertThat(actualState).isEqualTo(PickingState.STOP) },
        )
    }

    @Test
    fun `플레이어가 카드를 뽑았을 때, 최대 가능 총합을 초과하면, 입력 종료를 의미하는 클래스를 반환한다`() {
        val cards =
            setOf(
                Card.of(Shape.HEART, CardValue.TEN, 0),
                Card.of(Shape.DIAMOND, CardValue.TEN, 10),
            )
        val player = Player("haeum", cards) { "y" }
        val actualState =
            player.getCard {
                Card(Shape.DIAMOND.title, CardValue.TWO.title, CardValue.TWO.value)
            }
        assertAll(
            { assertThat(player.cards).hasSize(3) },
            { assertThat(actualState).isEqualTo(PickingState.STOP) },
        )
    }
}
