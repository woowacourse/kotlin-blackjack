package model.participants

import TestDeck
import model.card.Card
import model.result.Point.Companion.compareTo
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest {
    private lateinit var testDeck: TestDeck

    @BeforeEach
    fun setUp() {
        testDeck =
            TestDeck(
                mutableListOf(
                    Card.from(1),
                    Card.from(12),
                    Card.from(11),
                    Card.from(0),
                ),
            )
    }

    @Test
    fun `play가 끝나면 핸드의 합은 17 이상이다`() {
        val dealer = Dealer(Hand(testDeck))
        dealer.play()

        assertThat(dealer.hand.point >= 17).isTrue
    }
}
