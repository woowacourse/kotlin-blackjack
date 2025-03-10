package blackjack.domain

import blackjack.domain.card.CardFactoryImpl
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DeckTest {
    private lateinit var deck: Deck

    @BeforeEach
    fun setUp() {
        deck = Deck(CardFactoryImpl())
    }

    @Test
    fun `총 52개의 카드가 모두 소진되면 53번째 draw에서 예외가 발생한다`() {
        val cardMaxSize = 53
        assertThrows<IllegalArgumentException>(
            message = "카드가 모두 소진되었습니다.",
        ) {
            repeat(cardMaxSize) {
                deck.draw()
            }
        }
    }
}
