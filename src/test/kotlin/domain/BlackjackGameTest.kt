package domain

import domain.card.Card
import domain.card.CardValue
import domain.card.Shape
import domain.deck.Deck
import domain.judge.ParticipantResult
import domain.judge.Result
import domain.player.Names
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackjackGameTest {

    @Test
    fun `카드값 20을 가진 플레이어와 14를 가진 딜러를 비교하여 승을 반환한다`() {
        val deck = Deck(
            listOf(
                Card(Shape.SPADE, CardValue.KING),
                Card(Shape.DIAMOND, CardValue.QUEEN),
                Card(Shape.CLOVER, CardValue.JACK),
                Card(Shape.DIAMOND, CardValue.TEN),
                Card(Shape.HEART, CardValue.THREE),
                Card(Shape.SPADE, CardValue.ACE)
            )
        )
        val blackjackGame = BlackjackGame(Names(listOf("pingu")), deck)

        val value = blackjackGame.getPlayerWinningResult()

        assertThat(value).isEqualTo(listOf(ParticipantResult("pingu", Result.WIN)))
    }

    @Test
    fun `플레이어 3명이 승,패,패 일 때 딜러는 2승 1패이다`() {
        val actual =
            BlackjackGame(Names(listOf("jack", "king", "queen"))).judgeDealerResult(
                listOf(
                    ParticipantResult("jack", Result.WIN),
                    ParticipantResult("king", Result.LOSS),
                    ParticipantResult("queen", Result.LOSS)
                )
            )
        assertThat(actual).isEqualTo(listOf(Result.LOSS, Result.WIN, Result.WIN))
    }
}
