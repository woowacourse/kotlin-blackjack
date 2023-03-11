package domain

import domain.card.Card
import domain.card.CardValue
import domain.card.Cards
import domain.card.Shape
import domain.participants.Dealer
import domain.participants.Money
import domain.participants.Player
import domain.result.ParticipantsResult
import domain.result.PlayerResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackjackResultTest {

    @Test
    fun getResult() {
        val dealer = Dealer(Cards(mutableListOf(Card(Shape.SPADE, CardValue.TWO))))
        val players = listOf(Player("pingu", Cards(mutableListOf(Card(Shape.DIAMOND, CardValue.SIX))), Money(5000)))
        val blackjackResult = BlackjackResult(dealer, players)

        val expect = ParticipantsResult(dealer, listOf(PlayerResult(players.first(), 5000)))

        assertThat(blackjackResult.getResult()).isEqualTo(expect)
    }
}
