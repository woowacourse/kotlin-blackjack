package domain.gamer.state

import domain.card.Card
import domain.card.CardValue
import domain.card.Shape
import domain.gamer.Player
import domain.gamer.cards.Cards
import domain.judge.Result
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `뽑은 카드를 저장한다`() {
        val playerState = Player("jack", Cards(listOf()))
        playerState.pickCard(Card(Shape.CLOVER, CardValue.JACK))
        assertThat(playerState.cards.getCards()).isEqualTo(listOf(Card(Shape.CLOVER, CardValue.JACK)))
    }

    @Test
    fun `딜러가 버스트이고, 플레이어가 21 이하 일 때 플레이어가 이긴다`() {
        val dealerCards =
            Cards(
                listOf(
                    Card(Shape.SPADE, CardValue.JACK),
                    Card(Shape.SPADE, CardValue.QUEEN),
                    Card(Shape.SPADE, CardValue.THREE)
                )
            )
        val player = Player("jack", Cards(listOf(Card(Shape.HEART, CardValue.JACK))))
        val result = player.judgeResult(dealerCards)
        assertThat(result).isEqualTo(Result.WIN)
    }

    @Test
    fun `딜러가 버스트이고, 플레이어가 버스트 일 때 플레이어가 진다`() {
        val dealerCards =
            Cards(
                listOf(
                    Card(Shape.SPADE, CardValue.JACK),
                    Card(Shape.SPADE, CardValue.JACK),
                    Card(Shape.SPADE, CardValue.THREE)
                )
            )

        val player = Player(
            "jack",
            Cards(
                listOf(
                    Card(Shape.SPADE, CardValue.JACK),
                    Card(Shape.SPADE, CardValue.JACK),
                    Card(Shape.SPADE, CardValue.THREE)
                )
            )
        )
        val result = player.judgeResult(dealerCards)
        assertThat(result).isEqualTo(Result.LOSS)
    }

    @Test
    fun `딜러가 21이하이고, 플레이어가 버스트일 때 플레이어가 진다`() {
        val dealerCards = Cards(listOf(Card(Shape.HEART, CardValue.JACK)))
        val player = Player(
            "jack",
            Cards(
                listOf(
                    Card(Shape.SPADE, CardValue.JACK),
                    Card(Shape.SPADE, CardValue.JACK),
                    Card(Shape.SPADE, CardValue.THREE)
                )
            )

        )
        val result = player.judgeResult(dealerCards)
        assertThat(result).isEqualTo(Result.LOSS)
    }

    @Test
    fun `때 딜러가 블랙잭이고, 플레이어가 3장 합이 21일 때 플레이어가 진다`() {
        val dealerCards = Cards(listOf(Card(Shape.HEART, CardValue.JACK), Card(Shape.HEART, CardValue.ACE)))
        val player = Player(
            "jack",
            Cards(
                listOf(
                    Card(Shape.SPADE, CardValue.JACK),
                    Card(Shape.SPADE, CardValue.JACK),
                    Card(Shape.SPADE, CardValue.ACE)
                )
            )

        )
        val result = player.judgeResult(dealerCards)
        assertThat(result).isEqualTo(Result.LOSS)
    }

    @Test
    fun `딜러가 블랙잭이고, 플레이어가 블랙잭일 때 무승부이다`() {
        val dealerCards = Cards(listOf(Card(Shape.HEART, CardValue.JACK), Card(Shape.HEART, CardValue.ACE)))
        val player = Player(
            "jack",
            Cards(
                listOf(
                    Card(Shape.SPADE, CardValue.JACK),
                    Card(Shape.SPADE, CardValue.ACE)
                )
            )

        )
        val result = player.judgeResult(dealerCards)
        assertThat(result).isEqualTo(Result.DRAW)
    }

    @Test
    fun `딜러가 3장 합이 21이고, 플레이어가 블랙잭일 때 플레이어가 이긴다`() {
        val dealerCards =
            Cards(
                listOf(
                    Card(Shape.HEART, CardValue.JACK),
                    Card(Shape.HEART, CardValue.FIVE),
                    Card(Shape.HEART, CardValue.SIX)
                )
            )

        val player = Player(
            "jack",
            Cards(
                listOf(
                    Card(Shape.SPADE, CardValue.JACK),
                    Card(Shape.SPADE, CardValue.ACE)
                )
            )

        )
        val result = player.judgeResult(dealerCards)
        assertThat(result).isEqualTo(Result.WIN)
    }
}
