package domain.gamer.state

import domain.card.Card
import domain.card.CardValue
import domain.card.Shape
import domain.judge.Result
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerStateTest {
    @Test
    fun `뽑은 카드를 저장한다`() {
        val card = Card(Shape.HEART, CardValue.EIGHT)
        val dealerState = DealerState()
        dealerState.pickCard(card)
        assertThat(dealerState.cards).isEqualTo(listOf(card))
    }

    @Test
    fun `카드 값의 합을 반환한다`() {
        val dealerState =
            DealerState(mutableListOf(Card(Shape.SPADE, CardValue.JACK), Card(Shape.SPADE, CardValue.JACK)))
        assertThat(dealerState.calculateCardSum()).isEqualTo(20)
    }

    @Test
    fun `A가 2개 포함되어 있는 카드 값의 합을 반환한다`() {
        val dealerState =
            DealerState(mutableListOf(Card(Shape.SPADE, CardValue.ACE), Card(Shape.HEART, CardValue.ACE)))
        assertThat(dealerState.calculateCardSum()).isEqualTo(12)
    }

    @Test
    fun `A가 하나 있고 10 초과 있는 카드 값의 합을 반환한다`() {
        val dealerState =
            DealerState(mutableListOf(Card(Shape.SPADE, CardValue.JACK), Card(Shape.HEART, CardValue.THREE), Card(Shape.SPADE, CardValue.ACE)))
        assertThat(dealerState.calculateCardSum()).isEqualTo(14)
    }

    @Test
    fun `A가 두개 있고 10 초과 있는 카드 값의 합을 반환한다`() {
        val dealerState =
            DealerState(mutableListOf(Card(Shape.SPADE, CardValue.JACK), Card(Shape.SPADE, CardValue.TWO), Card(Shape.HEART, CardValue.ACE), Card(Shape.SPADE, CardValue.ACE)))
        assertThat(dealerState.calculateCardSum()).isEqualTo(14)
    }

    @Test
    fun `딜러 카드의 합이 16을 넘었을 경우 true를 반환한다`() {
        val dealerState =
            DealerState(mutableListOf(Card(Shape.SPADE, CardValue.JACK), Card(Shape.SPADE, CardValue.JACK)))
        assertThat(dealerState.checkAvailableForPick()).isTrue
    }

    @Test
    fun `딜러 카드의 합이 16을 넘지 않았을 경우 false 를 반환한다`() {
        val dealerState = DealerState(mutableListOf(Card(Shape.SPADE, CardValue.JACK)))
        assertThat(dealerState.checkAvailableForPick()).isFalse
    }

    @Test
    fun `플레이어 3명이 승,패,패 일 때 딜러는 2승 1패이다`() {
        val actual =
            DealerState().judgeDealerResult(mapOf("jack" to Result.WIN, "king" to Result.LOSS, "queen" to Result.LOSS))
        assertThat(actual).isEqualTo(listOf(Result.LOSS, Result.WIN, Result.WIN))
    }
}
