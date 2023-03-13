package blackjack.domain.state.running

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.state.State
import blackjack.domain.state.finished.Stay

abstract class Running(val cards: Cards) : State {
    override val isFinished = false

    abstract val toNextState: Boolean

    override fun draw(card: Card): State {
        cards.add(card)

        if (toNextState) return next()
        return this
    }

    override fun stay(): State = Stay(cards)

    abstract fun next(): State

    override fun getFirstCard(): Card = cards.getFirstCard()

    override fun getAllCards(): List<Card> = cards.items

    override fun getTotalScore(): Int = cards.calculateTotalScore()
}
