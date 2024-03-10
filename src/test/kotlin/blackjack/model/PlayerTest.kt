package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어 카드의 총합을 계산한다`() {
        val player =
            Player(
                gameInfo = GameInfo("해음").apply {
                    addCard(Card(Shape.CLOVER, CardRank.SIX))
                    addCard(Card(Shape.HEART, CardRank.K))
                },
            ) { "y" }

        assertThat(player.gameInfo.sumCardValues()).isEqualTo(16)
    }

    @Test
    fun `플레이어가 카드를 더 받는다고 응답하면, 보유 카드에 추가한다`() {
        val player =
            Player(
                GameInfo("해음").apply {
                    addCard(Card(Shape.CLOVER, CardRank.SIX))
                    addCard(Card(Shape.HEART, CardRank.K))
                },
            ) { "y" }

        player.drawCard {
            Card(Shape.DIAMOND, CardRank.SEVEN)
        }
        assertThat(player.gameInfo.cards).containsExactly(
            Card(Shape.CLOVER, CardRank.SIX),
            Card(Shape.HEART, CardRank.K),
            Card(Shape.DIAMOND, CardRank.SEVEN),
        )
    }

    @Test
    fun `플레이어가 카드를 더 받지 않겠다고 응답하면, 현재 보유 카드에 변경이 없도록 한다`() {
        val player = Player(GameInfo("haeum").apply { addCard(Card(Shape.HEART, CardRank.SIX)) }) { "n" }
        player.drawCard { Card(Shape.DIAMOND, CardRank.SEVEN) }

        assertThat(player.gameInfo.cards).containsExactly(Card(Shape.HEART, CardRank.SIX))
    }

    @Test
    fun `플레이어가 카드를 뽑았을 때, 최대 가능 총합을 초과하면, 입력 종료를 의미하는 클래스를 반환한다`() {
        val player = Player(
            GameInfo("haeum").apply {
                addCard(Card(Shape.HEART, CardRank.TEN))
                addCard(Card(Shape.DIAMOND, CardRank.TEN))
            },
        ) { "y" }
        val actualState =
            player.drawCard {
                Card(Shape.DIAMOND, CardRank.TWO)
            }

        assertThat(actualState).isEqualTo(PickingState.STAND)
    }
}
