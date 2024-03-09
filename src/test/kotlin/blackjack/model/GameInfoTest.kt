package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

fun getGameInfo(): GameInfo =
    GameInfo(
        name = "케이엠",
        cards = setOf(
            Card(Shape.CLOVER, CardRank.SIX),
            Card(Shape.HEART, CardRank.K),
        ),
    )

class GameInfoTest {
    @Test
    fun `카드를 한 장 추가하면, 카드의 개수가 하나 증가한다`() {
        val gameInfo = getGameInfo()
        gameInfo.addCard(Card(Shape.SPADE, CardRank.TWO))

        assertThat(gameInfo.cards).hasSize(3)
    }

    @Test
    fun `현재 보유하고 있는 카드들의 올바른 총합을 반환한다`() {
        val gameInfo = getGameInfo()
        val actualTotal = gameInfo.sumCardValues()
        val expectedTotal = 16
        assertThat(actualTotal).isEqualTo(expectedTotal)
    }
}
