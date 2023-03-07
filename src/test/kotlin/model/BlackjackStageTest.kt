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
    fun distributeAllUsers() {
        // given
        val cardFactory = ManualCardFactory(
            listOf(CardType.CLUB, CardType.SPADE, CardType.DIAMOND, CardType.CLUB), listOf(CardNumber.THREE, CardNumber.QUEEN, CardNumber.TWO, CardNumber.FOUR)
        )
        val players = Players()
        val dealer = Dealer()
        val blackjackStage = BlackjackStage(dealer, players, cardFactory)

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
        assertThat(blackjackStage.dealer.cards.value).isEqualTo(dealerExcept)
        assertThat(blackjackStage.players.value[0].cards.value).isEqualTo(playerExcept)
    }

    @Test
    fun distributePlayers() {
        // given
        val cardFactory = ManualCardFactory(
            listOf(CardType.CLUB, CardType.SPADE), listOf(CardNumber.THREE, CardNumber.QUEEN)
        )
        val players = Players()
        val dealer = Dealer()
        val blackjackStage = BlackjackStage(dealer, players, cardFactory)

        // when
        val actual = blackjackStage.distributePlayers { true }

        // then
        val except = listOf(
            Card(CardType.CLUB, CardNumber.THREE)
        )
        assertThat(actual?.cards?.value).isEqualTo(except)
    }

    @Test
    fun distributeDealer() {
        // given
        val cardFactory = ManualCardFactory(
            listOf(CardType.CLUB, CardType.SPADE), listOf(CardNumber.THREE, CardNumber.QUEEN)
        )
        val players = Players()
        val dealer = Dealer()
        val blackjackStage = BlackjackStage(dealer, players, cardFactory)

        // when
        blackjackStage.distributeDealer { }

        // then
        val except = listOf(
            Card(CardType.CLUB, CardNumber.THREE)
        )
        assertThat(blackjackStage.dealer.cards.value).isEqualTo(except)
    }
}
