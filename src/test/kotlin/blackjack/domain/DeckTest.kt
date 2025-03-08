package blackjack.domain

import blackjack.domain.card.FakeCardFactory
import blackjack.fixture.trumpCardFixture
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DeckTest {
    private lateinit var deck: Deck

    @BeforeEach
    fun setUp() {
        deck = Deck(FakeCardFactory(trumpCardFixture()))
    }

    @Test
    fun `카드가 모두 소진되면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException>(
            message = "카드가 모두 소진되었습니다.",
        ) {
            repeat(3) {
                deck.draw()
            }
        }
    }
}
