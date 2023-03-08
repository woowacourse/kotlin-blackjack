package domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.Cards
import blackjack.domain.card.Shape
import blackjack.domain.gameResult.GameResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameResultTest {

    @Test
    fun `player가 블랙잭이고 dealer가 블랙잭이 아닌 경우 player가 블랙잭 승리한다`() {
        assertThat(
            GameResult.valueOf(
                playerCards = Cards(
                    listOf(
                        Card(CardNumber.A, Shape.CLOVER),
                        Card(CardNumber.TEN, Shape.HEART)
                    )
                ),
                dealerCards = Cards(
                    listOf(
                        Card(CardNumber.A, Shape.CLOVER),
                        Card(CardNumber.FOUR, Shape.CLOVER)
                    )
                )
            )
        ).isEqualTo(GameResult.BLACKJACK_WIN)
    }

    @Test
    fun `dealer 카드의 합이 21점을 초과하게 되면 플레이어가 승리한다`() {
        val dealerCards = Cards(
            listOf(
                Card(CardNumber.K, Shape.CLOVER),
                Card(CardNumber.K, Shape.CLOVER)
            )
        )
        dealerCards.draw(Card(CardNumber.FOUR, Shape.CLOVER))

        assertThat(
            GameResult.valueOf(
                playerCards = Cards(
                    listOf(
                        Card(CardNumber.SEVEN, Shape.CLOVER),
                        Card(CardNumber.TEN, Shape.HEART)
                    )
                ),
                dealerCards = dealerCards
            )
        ).isEqualTo(GameResult.WIN)
    }

    @Test
    fun `player의 점수가 21점을 초과하고 dealer의 점수가 21점을 넘지 않으면 player는 패배한다`() {
        assertThat(
            GameResult.valueOf(
                playerCards = Cards(
                    listOf(
                        Card(CardNumber.A, Shape.CLOVER),
                        Card(CardNumber.A, Shape.HEART)
                    )
                ),
                dealerCards = Cards(
                    listOf(
                        Card(CardNumber.TEN, Shape.CLOVER),
                        Card(CardNumber.THREE, Shape.CLOVER)
                    )
                )
            )
        ).isEqualTo(GameResult.LOSE)
    }

    @Test
    fun `player와 Dealer의 점수가 21점이 넘지 않고, 점수가 같은 경우 무승부이다`() {
        assertThat(
            GameResult.valueOf(
                playerCards = Cards(
                    listOf(
                        Card(CardNumber.A, Shape.CLOVER),
                        Card(CardNumber.A, Shape.HEART)
                    )
                ),
                dealerCards = Cards(
                    listOf(
                        Card(CardNumber.A, Shape.CLOVER),
                        Card(CardNumber.A, Shape.CLOVER)
                    )
                )
            )
        ).isEqualTo(GameResult.DRAW)
    }

    @Test
    fun `player와 Dealer의 점수가 21점이 넘지 않고, player가 dealer보다 점수가 큰 경우 player가 승리한다`() {
        assertThat(
            GameResult.valueOf(
                playerCards = Cards(
                    listOf(
                        Card(CardNumber.FOUR, Shape.CLOVER),
                        Card(CardNumber.A, Shape.HEART)
                    )
                ),
                dealerCards = Cards(
                    listOf(
                        Card(CardNumber.THREE, Shape.CLOVER),
                        Card(CardNumber.TWO, Shape.CLOVER)
                    )
                )
            )
        ).isEqualTo(GameResult.WIN)
    }

    @Test
    fun `dealer와 player의 점수가 21점이 넘지 않고, dealer의 점수가 더 높은 경우 dealer는 승리한다`() {
        assertThat(
            GameResult.valueOf(
                playerCards = Cards(
                    listOf(
                        Card(CardNumber.TWO, Shape.CLOVER),
                        Card(CardNumber.A, Shape.HEART)
                    )
                ),
                dealerCards = Cards(
                    listOf(
                        Card(CardNumber.A, Shape.CLOVER),
                        Card(CardNumber.TEN, Shape.CLOVER)
                    )
                )
            )
        ).isEqualTo(GameResult.LOSE)
    }

    @Test
    fun `BLACKJACK_WIN의 반대는 LOSE`() {
        assertThat(!GameResult.BLACKJACK_WIN).isEqualTo(GameResult.LOSE)
    }

    @Test
    fun `WIN의 반대는 LOSE`() {
        assertThat(!GameResult.WIN).isEqualTo(GameResult.LOSE)
    }

    @Test
    fun `DRAW의 반대는 DRAW`() {
        assertThat(!GameResult.DRAW).isEqualTo(GameResult.DRAW)
    }

    @Test
    fun `LOSE의 반대는 WIN`() {
        assertThat(!GameResult.LOSE).isEqualTo(GameResult.WIN)
    }
}
