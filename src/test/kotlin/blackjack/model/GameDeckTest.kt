package blackjack.model

import blackjack.exception.Exceptions.NoCardErrorException
import blackjack.model.CardNumber.ACE
import blackjack.model.Pattern.HEART
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class GameDeckTest {
    @Test
    fun `카드 덱에 처음 나오는 문양과 숫자는 하트와 ACE이다`() {
        val cards = Card.of(listOf(Pair(HEART, ACE)))
        val card = cards.first()
        GameDeck.shuffleGameDeck(cards)
        Assertions.assertThat(GameDeck.drawCard()).isEqualTo(card)
    }

    @Test
    fun `카드 덱에 카드가 없다면 NoCardErrorException이 발생한다`() {
        GameDeck.shuffleGameDeck(listOf())
        assertThrows<NoCardErrorException> { GameDeck.drawCard() }
    }
}
