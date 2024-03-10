package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

fun getPlayers(numberOfPlayers: Int): List<String> {
    return List(numberOfPlayers) { "player$it" }
}

class DealerTest {

    @Test
    fun `딜러 카드의 총합을 계산한다`() {
        val gameInfo = GameInfo("딜러").apply {
            addCard(Card(Shape.CLOVER, CardRank.SIX))
            addCard(Card(Shape.HEART, CardRank.K))
        }

        val dealer = Dealer(gameInfo)

        assertThat(dealer.gameInfo.sumCardValues()).isEqualTo(16)
    }

    @Test
    fun `딜러의 카드 총합이 카드를 뽑을 수 있는 총합의 최댓값 이하일 때, 카드를 더 받을 수 있다`() {
        val gameInfo = GameInfo("딜러").apply {
            addCard(Card(Shape.CLOVER, CardRank.SIX))
            addCard(Card(Shape.HEART, CardRank.K))
        }
        val dealer = Dealer(gameInfo)
        val pickingState = dealer.drawCard { Card(Shape.CLOVER, CardRank.TWO) }

        val actualSize = dealer.gameInfo.cards.size
        val expectedSize = 3

        assertAll(
            { assertThat(actualSize).isEqualTo(expectedSize) },
            { assertThat(pickingState).isEqualTo(PickingState.HIT) },
        )
    }

    @Test
    fun `딜러의 카드 총합이 카드를 뽑을 수 있는 총합의 최댓값을 초과했을 때, 카드를 받지 않는다`() {
        val gameInfo = GameInfo("딜러").apply {
            addCard(Card(Shape.CLOVER, CardRank.SEVEN))
            addCard(Card(Shape.HEART, CardRank.K))
        }
        val dealer = Dealer(gameInfo)
        val pickingState = dealer.drawCard { Card(Shape.CLOVER, CardRank.TWO) }

        val actualSize = dealer.gameInfo.cards.size
        val expectedSize = 2

        assertAll(
            { assertThat(actualSize).isEqualTo(expectedSize) },
            { assertThat(pickingState).isEqualTo(PickingState.STAND) },
        )
    }

    @Test
    fun `딜러의 카드 총합이 카드를 뽑을 수 있는 총합의 최댓값 이하인 상태에서 1을 뽑았을 때, 올바른 총합을 계산한다`() {
        val gameInfo = GameInfo("딜러").apply {
            addCard(Card(Shape.CLOVER, CardRank.SIX))
            addCard(Card(Shape.HEART, CardRank.K))
        }
        val dealer = Dealer(gameInfo)
        dealer.drawCard { Card(Shape.CLOVER, CardRank.ACE) }

        val actualTotal = dealer.gameInfo.sumCardValues()
        val expectedTotal = 17

        assertThat(actualTotal).isEqualTo(expectedTotal)
    }

    @Test
    fun `딜러의 카드 총합이 카드를 뽑을 수 있는 총합의 최댓값을 초과한 상태에서 1을 뽑았을 때, 올바른 총합을 계산한다`() {
        val gameInfo = GameInfo("딜러").apply {
            addCard(Card(Shape.CLOVER, CardRank.SEVEN))
            addCard(Card(Shape.HEART, CardRank.K))
        }

        val dealer = Dealer(gameInfo)
        dealer.drawCard { Card(Shape.CLOVER, CardRank.ACE) }

        val actualTotal = dealer.gameInfo.sumCardValues()
        val expectedTotal = 17

        assertThat(actualTotal).isEqualTo(expectedTotal)
    }

    @Test
    fun `숫자가 1인 카드를 뽑았을 때, 딜러의 카드 총합에 따라서 올바른 총합을 계산한다`() {
        val gameInfo = GameInfo("딜러").apply {
            addCard(Card(Shape.HEART, CardRank.K))
        }
        val dealer = Dealer(gameInfo)
        dealer.drawCard { Card(Shape.CLOVER, CardRank.ACE) }

        val actualTotal = dealer.gameInfo.sumCardValues()
        val expectedTotal = 21

        assertThat(actualTotal).isEqualTo(expectedTotal)
    }
}
