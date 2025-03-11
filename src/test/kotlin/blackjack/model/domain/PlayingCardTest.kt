package blackjack.model.domain

import blackjack.model.domain.card.Card
import blackjack.model.domain.card.CardNumber
import blackjack.model.domain.card.PlayingCard
import blackjack.model.domain.card.Shape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayingCardTest {
    // given
    private val cards: ArrayDeque<Card> = ArrayDeque(listOf(Card(Shape.Diamond, CardNumber.Ace)))
    private val deck: PlayingCard = PlayingCard(cards)

    @Test
    fun `카드를 첫번째 장에 있는 카드를 나누어준다`() {
        // when
        val actual = deck.spreadCard()
        val expected = Card(Shape.Diamond, CardNumber.Ace)
        // then
        assertThat(actual).isEqualTo(expected)
    }
}
