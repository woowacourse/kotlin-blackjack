package blackjack.model

import java.util.LinkedList
import java.util.Queue

object CardDeck {
    private var cards: Queue<Card> = shuffledCards()

    fun pick(): Card =
        cards.poll() ?: run {
            cards = shuffledCards()
            pick()
        }

    private fun shuffledCards(): LinkedList<Card> {
        return LinkedList(
            Shape.entries.flatMap { shape ->
                CardValue.entries.map { cardValue ->
                    Card(shape, cardValue)
                }
            }.shuffled(),
        )
    }
}
