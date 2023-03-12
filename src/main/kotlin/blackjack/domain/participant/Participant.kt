package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.state.State
import blackjack.domain.state.finished.Blackjack
import blackjack.domain.state.finished.Bust
import blackjack.domain.state.running.Start

abstract class Participant {
    private var state: State = Start()
    val isFinished: Boolean
        get() = state.isFinished

    fun addCard(card: Card) {
        state = state.draw(card)
    }

    abstract fun getFirstOpenCards(): List<Card>

    abstract fun canDraw(): Boolean

    fun stay() {
        state = state.stay()
    }

    fun drawUntilPossible(deck: CardDeck, onDraw: (Participant) -> Unit) {
        while (!isFinished && canDraw()) {
            state = state.draw(deck.draw())
            onDraw(this)
        }
    }

    fun getFirstCard(): Card = state.getFirstCard()

    fun getCards(): List<Card> = state.getAllCards()

    fun getTotalScore(): Int = state.getTotalScore()

    fun isBlackjack(): Boolean = state is Blackjack

    fun isBust(): Boolean = state is Bust
}
