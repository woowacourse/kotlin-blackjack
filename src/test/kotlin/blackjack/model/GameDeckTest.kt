package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameDeckTest {
    @Test
    fun `카드 덱의 크기는 52개이다`() {
        val gameDeck = GameDeck()
        assertThat(gameDeck.cards.size).isEqualTo(GameDeck.DECK_SIZE)
    }

    @Test
    fun `카드 덱에서 카드 1장을 뽑아올 수 있다`() {
        val gameDeck = GameDeck()
        val initSize = gameDeck.cards.size
        val anotherCard = Card(Pattern.CLOVER, CardNumber.EIGHT)
        val cardFromDeck = gameDeck.drawCard()
        assertThat(gameDeck.cards.size).isEqualTo(initSize - 1)
        assertThat(cardFromDeck.javaClass).isEqualTo(anotherCard.javaClass)
    }
}
