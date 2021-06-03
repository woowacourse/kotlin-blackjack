package blackjack.domain.gamer

import blackjack.domain.card.Card
import blackjack.state.State
import blackjack.state.generateInitialState

abstract class Gamer(val name: String) {
    lateinit var state: State
    fun initializeCards(cards: MutableList<Card>) {
        state = generateInitialState(Hand(cards))
    }

    fun draw(card: Card) {
        state = state.draw(card)
    }

    fun score(): Score {
        return state.totalScore()
    }

    fun isBust(): Boolean {
        return state.isBust()
    }

    fun cards(): List<Card> {
        return state.cards()
    }


}
