package blackjack.model.domain

import blackjack.model.entitiy.Card
import blackjack.model.entitiy.CardRank
import blackjack.model.entitiy.Shape
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
                CardRank.entries.map { cardValue ->
                    Card(shape, cardValue)
                }
            }.shuffled(),
        )
    }
}
