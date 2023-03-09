package model

import entity.card.Card
import entity.card.CardNumber
import entity.card.CardType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardFactoryTest {
    @Test
    fun `플레이어들에게 카드 2장 씩 나누어 주었을 때 정상적으로 분배됐다`() {
        // given
        val manualCardFactory = ManualCardFactory(listOf(CardType.CLUB, CardType.SPADE), listOf(CardNumber.THREE, CardNumber.QUEEN))

        // when
        val except = listOf(Card(CardType.CLUB, CardNumber.THREE), Card(CardType.SPADE, CardNumber.QUEEN))
        val cards = manualCardFactory.generate(2)

        // then
        assertThat(cards.value).isEqualTo(except)
    }
}
