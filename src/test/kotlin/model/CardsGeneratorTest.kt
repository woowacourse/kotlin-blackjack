package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsGeneratorTest {
    @Test
    fun `게임에 사용될 52장의 카드를 생성한다`() {
        val cards = CardsGenerator().generateCards()
        val cardsLength = cards.allCards.size
        assertThat(cardsLength).isEqualTo(52)
    }
}
