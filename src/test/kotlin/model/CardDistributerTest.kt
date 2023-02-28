package model

import entity.Card
import entity.CardNumber
import entity.CardType
import entity.Cards
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardDistributerTest() {
    @Test
    fun `플레이어들에게 카드 1장 씩 나누어 주었을 때 정상적으로 분배됐다`() {
        val manualCardFactory = ManualCardFactory(listOf(CardType.CLUB), listOf(CardNumber.THREE))
        val cardDistributer = CardDistributer(manualCardFactory)
        val player = Player(Cards(mutableListOf()))

        // when
        val except = Card(CardType.CLUB, CardNumber.THREE)

        cardDistributer.distribute(player, 1)
        assertThat(player.cards.value[0]).isEqualTo(except)
    }
}
