package state

import domain.Score
import domain.card.Card
import domain.card.Cards

abstract class StartedState(protected val cards: Cards) : State {
    override val score: Score
        get() = cards.score

    override fun getCards(): List<Card> {
        return cards.list.toList()
    }
}
