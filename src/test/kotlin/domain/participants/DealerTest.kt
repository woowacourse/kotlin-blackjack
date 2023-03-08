package domain.participants

import domain.card.Card
import domain.card.CardValue
import domain.card.Cards
import domain.card.Shape
import domain.judge.ParticipantResult
import domain.judge.Result
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러 카드의 합이 16을 넘었을 경우 true를 반환한다`() {
        val dealer =
            Dealer(Cards(mutableListOf(Card(Shape.SPADE, CardValue.JACK), Card(Shape.SPADE, CardValue.JACK))))
        assertThat(dealer.checkOverCondition()).isTrue
    }

    @Test
    fun `딜러 카드의 합이 16을 넘지 않았을 경우 false를 반환한다`() {
        val dealer = Dealer(Cards(mutableListOf(Card(Shape.SPADE, CardValue.JACK))))
        assertThat(dealer.checkOverCondition()).isFalse
    }

    @Test
    fun `참가자가 블랙잭이고 딜러가 합이 21일 경우, 플레이어가 이긴다`() {
        val dealerCards = Cards(
            mutableListOf(
                Card(Shape.SPADE, CardValue.JACK),
                Card(Shape.SPADE, CardValue.QUEEN),
                Card(Shape.SPADE, CardValue.ACE)
            )
        )
        val dealer = Dealer(dealerCards)
        val playerCards = Cards(mutableListOf(Card(Shape.HEART, CardValue.JACK), Card(Shape.HEART, CardValue.ACE)))
        val player = Player("jack", playerCards)

        val referee = dealer.judgePlayersResult(listOf(player))

        assertThat(referee).isEqualTo(listOf(ParticipantResult("jack", Result.BLACKJACK_WIN)))
    }

    @Test
    fun `참가자와 딜러 모두 블랙잭인 경우, 비긴다`() {
        val dealer = Dealer(
            Cards(
                mutableListOf(
                    Card(Shape.SPADE, CardValue.JACK),
                    Card(Shape.SPADE, CardValue.ACE)
                )
            )
        )
        val player =
            Player("jack", Cards(mutableListOf(Card(Shape.HEART, CardValue.JACK), Card(Shape.HEART, CardValue.ACE))))
        val referee = dealer.judgePlayersResult(listOf(player))
        assertThat(referee).isEqualTo(listOf(ParticipantResult("jack", Result.DRAW)))
    }

    @Test
    fun `참가자가 합이 21이고 딜러가 블랙잭인 경우, 플레이어가 진다`() {
        val dealer = Dealer(
            Cards(
                mutableListOf(
                    Card(Shape.SPADE, CardValue.JACK),
                    Card(Shape.SPADE, CardValue.ACE)
                )
            )
        )
        val player =
            Player(
                "jack",
                Cards(
                    mutableListOf(
                        Card(Shape.HEART, CardValue.JACK),
                        Card(Shape.HEART, CardValue.QUEEN),
                        Card(Shape.HEART, CardValue.ACE)
                    )
                )
            )
        val referee = dealer.judgePlayersResult(listOf(player))
        assertThat(referee).isEqualTo(listOf(ParticipantResult("jack", Result.LOSS)))
    }

    @Test
    fun `딜러가 버스트이고, 플레이어가 21 이하 일 때 플레이어가 이긴다`() {
        val dealer = Dealer(
            Cards(
                mutableListOf(
                    Card(Shape.SPADE, CardValue.JACK),
                    Card(Shape.SPADE, CardValue.QUEEN),
                    Card(Shape.SPADE, CardValue.THREE)
                )
            )
        )
        val player = Player("jack", Cards(mutableListOf(Card(Shape.HEART, CardValue.JACK))))
        val referee = dealer.judgePlayersResult(listOf(player))
        assertThat(referee).isEqualTo(listOf(ParticipantResult("jack", Result.WIN)))
    }

    @Test
    fun `딜러가 버스트이고, 플레이어가 버스트 일 때 플레이어가 진다`() {
        val dealer = Dealer(
            Cards(
                mutableListOf(
                    Card(Shape.SPADE, CardValue.JACK),
                    Card(Shape.SPADE, CardValue.JACK),
                    Card(Shape.SPADE, CardValue.THREE)
                )
            )
        )
        val player = Player(
            "jack",
            Cards(
                mutableListOf(
                    Card(Shape.SPADE, CardValue.JACK),
                    Card(Shape.SPADE, CardValue.JACK),
                    Card(Shape.SPADE, CardValue.THREE)
                )
            )
        )
        val referee = dealer.judgePlayersResult(listOf(player))
        assertThat(referee).isEqualTo(
            listOf(ParticipantResult("jack", Result.LOSS))
        )
    }

    @Test
    fun `딜러가 21이하이고, 플레이어가 버스트일 때 플레이어가 진다`() {
        val dealer = Dealer(Cards(mutableListOf(Card(Shape.HEART, CardValue.JACK))))
        val player = Player(
            "jack",
            Cards(
                mutableListOf(
                    Card(Shape.SPADE, CardValue.JACK),
                    Card(Shape.SPADE, CardValue.JACK),
                    Card(Shape.SPADE, CardValue.THREE)
                )
            )
        )
        val referee = dealer.judgePlayersResult(listOf(player))
        assertThat(referee).isEqualTo(listOf(ParticipantResult("jack", Result.LOSS)))
    }

    @Test
    fun `플레이어 두명이 각 15,21의 결과값을 갖고 딜러가 20일 때, 패,승을 반환한다`() {
        val dealer =
            Dealer(
                Cards(mutableListOf(Card(Shape.HEART, CardValue.JACK), Card(Shape.HEART, CardValue.QUEEN)))
            )
        val player1 = Player(
            "jack",
            Cards(
                mutableListOf(
                    Card(Shape.SPADE, CardValue.JACK),
                    Card(Shape.SPADE, CardValue.FIVE)
                )
            )
        )
        val player2 = Player(
            "king",
            Cards(
                mutableListOf(
                    Card(Shape.SPADE, CardValue.QUEEN),
                    Card(Shape.SPADE, CardValue.KING),
                    Card(Shape.SPADE, CardValue.ACE)
                )
            )
        )
        val referee = dealer.judgePlayersResult(listOf(player1, player2))
        assertThat(referee).isEqualTo(
            listOf(ParticipantResult("jack", Result.LOSS), ParticipantResult("king", Result.WIN))
        )
    }
}
