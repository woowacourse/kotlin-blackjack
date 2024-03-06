package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HandCardTest{
    class HandCard(private val cards: List<Card>){

        fun totalSumCards(): Int{
           return cards.sumOf{card -> card.getScore() }
        }
    }

    @Test
    fun `손패에서 카드들의 숫자를 올바르게 반환하는지 테스트`(){
        val actualCard =
            HandCard(
                cards = listOf(
            Card(Denomination.KING,Suit.SPADE),
            Card(Denomination.FOUR,Suit.SPADE),
            Card(Denomination.FIVE,Suit.SPADE),
                )
            )
        assertThat(actualCard.totalSumCards()).isEqualTo(19)
    }
}
