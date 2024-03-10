package blackjack.model.card

import blackjack.fixture.createCard
import blackjack.fixture.createDeck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `마지막 카드가 Ace 일 때, 카드를 뽑으면 Ace 카드가 나온다`() {
        // given
        val aceCard = createCard(rank = Rank.ACE)
        val deck = createDeck(aceCard)
        // when
        val actual = deck.draw()
        // then
        assertThat(actual).isEqualTo(aceCard)
    }

    @Test
    fun `Ace, Queen 순서대로 가장 위에 있을 때, 2장을 뽑으면 Queen, Ace 순서대로 나온다`() {
        // given
        val cards = mutableListOf(createCard(rank = Rank.ACE), createCard(rank = Rank.QUEEN))
        val deck = Deck(cards)
        val expected = listOf(createCard(rank = Rank.QUEEN), createCard(rank = Rank.ACE))
        // when
        val actual = deck.drawMultiple(2)
        // then
        assertThat(actual).isEqualTo(expected)
    }
}
