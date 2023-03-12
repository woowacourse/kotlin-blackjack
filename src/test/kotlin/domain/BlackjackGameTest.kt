package domain

import domain.card.Card
import domain.card.CardValue
import domain.card.Shape
import domain.deck.Deck
import domain.judge.Result
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackjackGameTest {
    @Test
    fun `플레이어 3명이 승,패,패 일 때 딜러는 2승 1패이다`() {
        val actual =
            BlackjackGame(Deck(listOf())).judgeDealerResult(
                mapOf(
                    "jack" to Result.WIN,
                    "king" to Result.LOSS,
                    "queen" to Result.LOSS
                )
            )
        assertThat(actual).isEqualTo(listOf(Result.LOSS, Result.WIN, Result.WIN))
    }

    @Test
    fun `test`() {
        val card1 = Card(Shape.SPADE, CardValue.ACE)
        val card2 = Card(Shape.SPADE, CardValue.ACE)
        println(card1.hashCode())
        print(card2.hashCode())
        assertThat(card1).isEqualTo(card2)
    }
}
