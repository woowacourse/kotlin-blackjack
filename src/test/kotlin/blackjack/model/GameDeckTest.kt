package blackjack.model

import blackjack.model.CardNumber.ACE
import blackjack.model.Pattern.HEART
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalStateException

class GameDeckTest {
    @Test
    fun `카드 덱에 처음 나오는 문양과 숫자는 하트와 ACE이다`() {
        val cards = Card.of(listOf(Pair(HEART, ACE)))
        val card = cards.first()
        val gameDeck = GameDeck()

        gameDeck.resetDeck(cards)
        Assertions.assertThat(gameDeck.drawCard()).isEqualTo(card)
    }

    @Test
    fun `카드 덱에 카드가 없다면 IllegalStateException이 발생한다`() {
        val gameDeck = GameDeck()

        gameDeck.resetDeck(listOf())

        assertThrows<IllegalStateException> { gameDeck.drawCard() }
    }
}
