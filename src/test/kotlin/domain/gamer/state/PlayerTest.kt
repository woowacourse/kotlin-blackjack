package domain.gamer.state

import domain.CloverCardsOf
import domain.DiamondCardsOf
import domain.HeartCardsOf
import domain.SpadeCardsOf
import domain.card.Card
import domain.card.CardValue.ACE
import domain.card.CardValue.FIVE
import domain.card.CardValue.JACK
import domain.card.CardValue.QUEEN
import domain.card.CardValue.SEVEN
import domain.card.CardValue.SIX
import domain.card.CardValue.TEN
import domain.card.CardValue.THREE
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
        playerState.pickCard(Card(Shape.CLOVER, JACK))
        assertThat(playerState.cards.getCards()).isEqualTo(listOf(Card(Shape.CLOVER, JACK)))
    }

    @Test
    fun `딜러가 버스트이고, 플레이어가 21 이하 일 때 플레이어가 이긴다`() {
        val dealerCards =
            CloverCardsOf(JACK, QUEEN, THREE)
        val player = Player("jack", HeartCardsOf(SEVEN))
        val result = player.judgeResult(dealerCards)
        assertThat(result).isEqualTo(Result.WIN)
    }

    @Test
    fun `딜러가 버스트이고, 플레이어가 버스트 일 때 플레이어가 진다`() {
        val dealerCards =
            SpadeCardsOf(JACK, QUEEN, THREE)

        val player = Player(
            "jack",
            DiamondCardsOf(JACK, QUEEN, THREE)
        )
        val result = player.judgeResult(dealerCards)
        assertThat(result).isEqualTo(Result.LOSS)
    }

    @Test
    fun `딜러가 21이하이고, 플레이어가 버스트일 때 플레이어가 진다`() {
        val dealerCards = HeartCardsOf(ACE, TEN)
        val player = Player(
            "jack",
            HeartCardsOf(JACK, QUEEN, THREE)

        )
        val result = player.judgeResult(dealerCards)
        assertThat(result).isEqualTo(Result.LOSS)
    }

    @Test
    fun `때 딜러가 블랙잭이고, 플레이어가 3장 합이 21일 때 플레이어가 진다`() {
        val dealerCards = SpadeCardsOf(ACE, JACK)
        val player = Player(
            "jack",
            HeartCardsOf(JACK, JACK, ACE)

        )
        val result = player.judgeResult(dealerCards)
        assertThat(result).isEqualTo(Result.LOSS)
    }

    @Test
    fun `딜러가 블랙잭이고, 플레이어가 블랙잭일 때 무승부이다`() {
        val dealerCards = SpadeCardsOf(ACE, JACK)
        val player = Player(
            "jack",
            CloverCardsOf(ACE, JACK)

        )
        val result = player.judgeResult(dealerCards)
        assertThat(result).isEqualTo(Result.DRAW)
    }

    @Test
    fun `딜러가 3장 합이 21이고, 플레이어가 블랙잭일 때 플레이어가 이긴다`() {
        val dealerCards =
            SpadeCardsOf(FIVE, JACK, SIX)

        val player = Player(
            "jack",
            CloverCardsOf(ACE, JACK)

        )
        val result = player.judgeResult(dealerCards)
        assertThat(result).isEqualTo(Result.WIN)
    }
}
