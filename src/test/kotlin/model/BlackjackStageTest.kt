package model

import entity.Card
import entity.CardNumber
import entity.CardType
import entity.Dealer
import entity.Money
import entity.Name
import entity.Player
import entity.Players
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackjackStageTest {

    private fun Players(): Players {
        return Players(listOf(Player(Name("test"), Money(0))))
    }

    @Test
    fun `딜러와 모든 플레이어에게 카드 2장씩 분배한다`() {
        // given
        val cardFactory = ManualCardFactory(
            listOf(
                CardType.CLUB to CardNumber.THREE,
                CardType.SPADE to CardNumber.QUEEN,
                CardType.DIAMOND to CardNumber.TWO,
                CardType.CLUB to CardNumber.FOUR
            )
        )
        val players = Players()
        val dealer = Dealer()
        val users = Users(players, dealer)
        val blackjackStage = BlackjackStage(users, cardFactory)

        // when
        blackjackStage.distributeAllUsers()

        // then
        val dealerExcept = listOf(
            Card(CardType.CLUB, CardNumber.THREE),
            Card(CardType.SPADE, CardNumber.QUEEN),
        )
        val playerExcept = listOf(
            Card(CardType.DIAMOND, CardNumber.TWO),
            Card(CardType.CLUB, CardNumber.FOUR),
        )
        assertThat(blackjackStage.users.dealer.cards.value).isEqualTo(dealerExcept)
        assertThat(blackjackStage.users.players.value[0].cards.value).isEqualTo(playerExcept)
    }

    @Test
    fun `모든 플레이어에게 카드를 21이 넘을 때 까지 분배한다`() {
        // given
        val cardFactory = ManualCardFactory(
            listOf(
                CardType.CLUB to CardNumber.FIVE,
                CardType.CLUB to CardNumber.TEN,
                CardType.CLUB to CardNumber.TEN,
                CardType.CLUB to CardNumber.FOUR
            )
        )
        val players = Players()
        val dealer = Dealer()
        val users = Users(players, dealer)
        val blackjackStage = BlackjackStage(users, cardFactory)

        // when
        blackjackStage.distributePlayers({ true }) {}

        // then
        val except = listOf(
            Card(CardType.CLUB, CardNumber.FIVE),
            Card(CardType.CLUB, CardNumber.TEN),
            Card(CardType.CLUB, CardNumber.TEN)
        )
        assertThat(blackjackStage.users.players.value[0].cards.value).isEqualTo(except)
    }

    @Test
    fun `딜러에게 카드 한장 분배한다`() {
        // given
        val cardFactory = ManualCardFactory(
            listOf(
                CardType.CLUB to CardNumber.THREE,
                CardType.SPADE to CardNumber.QUEEN
            )
        )
        val players = Players()
        val dealer = Dealer()
        val users = Users(players, dealer)
        val blackjackStage = BlackjackStage(users, cardFactory)

        // when
        blackjackStage.distributeDealer { }

        // then
        val except = listOf(
            Card(CardType.CLUB, CardNumber.THREE)
        )
        assertThat(blackjackStage.users.dealer.cards.value).isEqualTo(except)
    }
}
