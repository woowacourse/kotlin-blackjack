package model.participants

import DeckExplicitGenerationStrategy
import model.card.Card
import model.card.Deck
import model.card.MarkType
import model.card.ValueType
import model.result.Point
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class HandTest {
    private lateinit var hand: Hand
    private lateinit var deck: Deck

    @BeforeEach
    fun setUp() {
        deck =
            Deck.create(
                DeckExplicitGenerationStrategy(
                    mutableListOf(
                        Card(ValueType.TWO, MarkType.SPADE),
                        Card(ValueType.THREE, MarkType.SPADE),
                    ),
                ),
            )
        hand = Hand()
    }

    @Test
    fun `카드를 한장 추가할 수 있다`() {
        assertDoesNotThrow {
            hand.draw(deck.pop())
        }
    }

    @Test
    fun `카드를 한장 추가할 경우 핸드의 사이즈는 1 증가한다`() {
        val size = hand.cards.size
        hand.draw(deck.pop())

        assertThat(hand.cards.size).isEqualTo(size + 1)
    }

    @Test
    fun `핸드 내의 카드 값의 합을 구할 수 있다`() {
        hand.draw(deck.pop())
        hand.draw(deck.pop())
        assertThat(hand.point == Point(5))
    }
}
