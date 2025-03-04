package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Deck {
    private val _cards = mutableListOf<Card>()
    val cards: List<Card> get() = _cards.toList()

    init {
        _cards.addAll(generateDeck())
        require(cards.size == DECK_SIZE) { INVALID_DECK_SIZE_ERROR_MESSAGE }
    }

    private fun generateDeck(): List<Card> = CardPattern.entries.flatMap(::createCard)

    private fun createCard(cardPattern: CardPattern): List<Card> {
        return CardNumber.entries.map { cardNumber -> Card.create(cardNumber, cardPattern) }
    }

    companion object {
        private const val DECK_SIZE = 52
        private const val INVALID_DECK_SIZE_ERROR_MESSAGE = "덱은 52장의 카드로 구성되어야 합니다."
    }
}

class DeckTest {
    @Test
    fun `고유한 52장의 카드를 가지고 있어야 한다`() {
        val deck = Deck()

        deck.cards.size shouldBe 52
    }
}
