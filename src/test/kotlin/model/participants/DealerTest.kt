package model.participants

import DeckExplicitGenerationStrategy
import model.card.Card
import model.card.Deck
import model.card.MarkType
import model.card.ValueType
import model.result.Point.Companion.compareTo
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest {
    private lateinit var testDeck: Deck

    @BeforeEach
    fun setUp() {
        testDeck =
            Deck.create(
                DeckExplicitGenerationStrategy(
                    mutableListOf(
                        Card(ValueType.TWO, MarkType.SPADE),
                        Card(ValueType.JACK, MarkType.SPADE),
                        Card(ValueType.QUEEN, MarkType.SPADE),
                        Card(ValueType.ACE, MarkType.SPADE),
                    ),
                ),
            )
    }

    @Test
    fun `play가 끝나면 핸드의 합은 17 이상이다`() {
        val dealer = Dealer(ParticipantState.Playing(Hand()))
        dealer.play(testDeck)

        assertThat(dealer.participantState.hand.point >= 17).isTrue
    }
}
