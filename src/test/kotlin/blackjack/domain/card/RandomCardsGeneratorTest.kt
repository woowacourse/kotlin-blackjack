package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RandomCardsGeneratorTest {
    @Test
    fun `카드를 52장 생성한다`() {
        // given
        val deckGenerator = RandomCardsGenerator()

        // when
        val cardDeck = deckGenerator.generate()
        val actual = cardDeck.size

        // then
        assertThat(actual).isEqualTo(52)
    }

    @Test
    fun `생성된 카드는 랜덤한 순서를 갖는다`() {
        // given
        val deckGenerator1 = RandomCardsGenerator()
        val deckGenerator2 = RandomCardsGenerator()

        // when
        val cardDeck1 = deckGenerator1.generate()
        val cardDeck2 = deckGenerator2.generate()

        val actual1 = listOf(cardDeck1[0].number, cardDeck1[0].shape)
        val actual2 = listOf(cardDeck2[0].number, cardDeck2[0].shape)

        // then
        assertThat(actual1).isNotEqualTo(actual2)
    }
}
