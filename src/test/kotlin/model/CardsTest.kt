package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {
    @Test
    fun `원하는 개수 만큼 카드를 뽑을 수 있다`(){
        val cards = CardsGenerator().generateCards().allCards
        val drawCards = Cards(cards).drawCards(2)

        assertThat(drawCards.size).isEqualTo(2)
    }

    @Test
    fun `지정된 개수만큼 카드를 뽑은 후 남은 카드를 반환한다`(){
        val cards = CardsGenerator().generateCards()
        cards.drawCards(2)
        assertThat(cards.allCards.size).isEqualTo(50)
    }
}
