package model

import entity.Card
import entity.CardNumber
import entity.CardType
import entity.Cards
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardDistributerTest() {
    @Test
    fun `플레이어들에게 카드 2장 씩 나누어 주었을 때 정상적으로 분배됐다`() {
        // given
        val manualCardFactory = ManualCardFactory(listOf(CardType.CLUB, CardType.SPADE), listOf(CardNumber.THREE, CardNumber.QUEEN))
        val cardDistributer = CardDistributer(manualCardFactory)
        val user = User(Cards(mutableListOf()))

        // when
        val except = listOf(Card(CardType.CLUB, CardNumber.THREE), Card(CardType.SPADE, CardNumber.QUEEN))
        cardDistributer.distribute(user, 2)

        // then
        assertThat(user.cards.value).isEqualTo(except)
    }
}
