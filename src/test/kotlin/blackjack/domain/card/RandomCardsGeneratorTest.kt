package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RandomCardsGeneratorTest {
    @Test
    fun `RandomCardsGenerator는 카드를 52장 생성한다`() {
        // given
        val cardsGenerator = RandomCardsGenerator()

        // when
        val cards: Cards = cardsGenerator.generate()
        val actual = cards.getSize()

        // then
        assertThat(actual).isEqualTo(52)
    }

    @Test
    fun `RandomCardsGenerator로 생성된 카드는 랜덤한 순서를 갖는다`() {
        // given
        val cardsGenerator1 = RandomCardsGenerator()
        val cardsGenerator2 = RandomCardsGenerator()

        // when
        val actual1: Card = cardsGenerator1.generate().values[0]
        val actual2: Card = cardsGenerator2.generate().values[0]

        // then
        assertThat(actual1).isNotEqualTo(actual2)
    }
}
