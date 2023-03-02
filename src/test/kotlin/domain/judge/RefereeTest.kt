package domain.judge

import domain.card.Card
import domain.card.CardValue
import domain.card.Shape
import domain.gamer.state.DealerState
import domain.gamer.state.PlayerState
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RefereeTest() {

    @Test
    fun `참가자가 한명일 때 딜러가 버스트이고, 플레이어가 21 이하 일 때 플레이어가 이긴다`() {
        val dealerState = DealerState(
            mutableListOf(
                Card(Shape.SPADE, CardValue.JACK),
                Card(Shape.SPADE, CardValue.QUEEN),
                Card(Shape.SPADE, CardValue.THREE)
            )
        )
        val playerState = PlayerState(mutableListOf(Card(Shape.HEART, CardValue.JACK)))
        val referee = Referee(dealerState, mapOf("jack" to playerState)).judgePlayersResult()
        assertThat(referee).isEqualTo(mapOf("jack" to Result.WIN))
    }

    @Test
    fun `참가자가 한명일 때 딜러가 버스트이고, 플레이어가 버스트 일 때 플레이어가 진다`() {
        val dealerState = DealerState(
            mutableListOf(
                Card(Shape.SPADE, CardValue.JACK),
                Card(Shape.SPADE, CardValue.JACK),
                Card(Shape.SPADE, CardValue.THREE)
            )
        )
        val playerState = PlayerState(
            mutableListOf(
                Card(Shape.SPADE, CardValue.JACK),
                Card(Shape.SPADE, CardValue.JACK),
                Card(Shape.SPADE, CardValue.THREE)
            )
        )
        val referee = Referee(dealerState, mapOf("jack" to playerState)).judgePlayersResult()
        assertThat(referee).isEqualTo(mapOf("jack" to Result.LOSS))
    }

    @Test
    fun `참가자가 한명일 때 딜러가 21이하이고, 플레이어가 버스트일 때 플레이어가 진다`() {
        val dealerState = DealerState(mutableListOf(Card(Shape.HEART, CardValue.JACK)))
        val playerState = PlayerState(
            mutableListOf(
                Card(Shape.SPADE, CardValue.JACK),
                Card(Shape.SPADE, CardValue.JACK),
                Card(Shape.SPADE, CardValue.THREE)
            )
        )
        val referee = Referee(dealerState, mapOf("jack" to playerState)).judgePlayersResult()
        assertThat(referee).isEqualTo(mapOf("jack" to Result.LOSS))
    }

    @Test
    fun `플레이어 두명이 각 15,21의 결과값이고 딜러가 20일 때, 패,승을 반환한다`() {
        val dealerState =
            DealerState(mutableListOf(Card(Shape.HEART, CardValue.JACK), Card(Shape.HEART, CardValue.QUEEN)))
        val playerState1 = PlayerState(
            mutableListOf(
                Card(Shape.SPADE, CardValue.JACK),
                Card(Shape.SPADE, CardValue.FIVE)
            )
        )
        val playerState2 = PlayerState(
            mutableListOf(
                Card(Shape.SPADE, CardValue.QUEEN),
                Card(Shape.SPADE, CardValue.KING),
                Card(Shape.SPADE, CardValue.ACE)
            )
        )
        val referee = Referee(dealerState, mapOf("jack" to playerState1, "king" to playerState2)).judgePlayersResult()
        assertThat(referee).isEqualTo(mapOf("jack" to Result.LOSS, "king" to Result.WIN))
    }

    @Test
    fun `플레이어 세명이 각 10,23,17 결과값이고 딜러가 17 일 때 패,패,무 를 반환한다`() {
        val dealerState =
            DealerState(mutableListOf(Card(Shape.HEART, CardValue.SEVEN), Card(Shape.HEART, CardValue.QUEEN)))
        val playerState1 = PlayerState(
            mutableListOf(
                Card(Shape.DIAMOND, CardValue.FIVE),
                Card(Shape.SPADE, CardValue.FIVE)
            )
        )
        val playerState2 = PlayerState(
            mutableListOf(
                Card(Shape.CLOVER, CardValue.QUEEN),
                Card(Shape.DIAMOND, CardValue.KING),
                Card(Shape.DIAMOND, CardValue.THREE)
            )
        )
        val playerState3 = PlayerState(
            mutableListOf(
                Card(Shape.HEART, CardValue.QUEEN),
                Card(Shape.CLOVER, CardValue.SEVEN)
            )
        )
        val referee = Referee(
            dealerState,
            mapOf("jack" to playerState1, "king" to playerState2, "queen" to playerState3)
        ).judgePlayersResult()
        assertThat(referee).isEqualTo(mapOf("jack" to Result.LOSS, "king" to Result.LOSS, "queen" to Result.DRAW))
    }
}
