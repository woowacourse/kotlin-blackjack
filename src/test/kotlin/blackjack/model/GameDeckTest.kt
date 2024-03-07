package blackjack.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class GameDeckTest {
    @Test
    fun `카드 덱의 크기는 52개이다`() {
        val gameDeck = GameDeck()
        Assertions.assertThat(gameDeck.cards.size).isEqualTo(GameDeck.DECK_SIZE)
    }
}
