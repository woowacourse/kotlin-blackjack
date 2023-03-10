package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

interface State {
    val cards: Cards
    val score: Score
    val size: Int
    val ratio: Double
    fun draw(card: Card): State
}
