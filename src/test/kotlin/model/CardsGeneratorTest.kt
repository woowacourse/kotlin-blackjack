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

    @Test
    fun `TestShuffler를 사용하면 생성된 카드의 순서를 예측할 수 있다`() {
        val cards = CardsGenerator(TestShuffler()).generateCards().allCards

        val expectedCards = mutableListOf<Card>()
        Shape.entries.forEach { shape ->
            CardRank.entries.forEach { rank ->
                expectedCards.add(Card.of(rank, shape))
            }
        }

        assertThat(cards).isEqualTo(expectedCards)
    }
}
