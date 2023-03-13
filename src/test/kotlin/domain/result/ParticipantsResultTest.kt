package domain.result

import domain.card.Card
import domain.card.CardValue
import domain.card.Cards
import domain.card.Shape
import domain.participants.Dealer
import domain.participants.Money
import domain.participants.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantsResultTest {
    @Test
    fun getDealerProfitTest() {
        val dealer = Dealer(Cards(mutableListOf(Card(Shape.SPADE, CardValue.TEN))))
        val player = Player("pingu", Cards(mutableListOf(Card(Shape.SPADE, CardValue.TWO))), Money(1000))
        val playerResult = listOf(PlayerResult(player, -1000), PlayerResult(player, -2000))

        val participantsResult = ParticipantsResult(dealer, playerResult)

        assertThat(participantsResult.getDealerProfit()).isEqualTo(3000)
    }
}
