package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameInfoTest {
    @Test
    fun `카드를 한 장 추가하면, 카드의 개수가 하나 증가한다`() {
        val gameInfo = GameInfo("해음").apply {
            addCard(Card(Shape.SPADE, CardRank.TWO))
        }

        assertThat(gameInfo.cards).hasSize(1)
    }

    @Test
    fun `추가한 카드가 카드 뭉치에 추가된다`() {
        val gameInfo = GameInfo("해음").apply {
            addCard(Card(Shape.SPADE, CardRank.TWO))
        }

        assertThat(gameInfo.cards).isEqualTo(setOf(Card(Shape.SPADE, CardRank.TWO)))
    }

    @Test
    fun `현재 보유하고 있는 카드들의 올바른 총합을 반환한다`() {
        val gameInfo = GameInfo("해음").apply {
            addCard(Card(Shape.SPADE, CardRank.ACE))
            addCard(Card(Shape.SPADE, CardRank.TEN))
        }

        val actualTotal = gameInfo.sumCardValues()
        val expectedTotal = 21
        assertThat(actualTotal).isEqualTo(expectedTotal)
    }
}
