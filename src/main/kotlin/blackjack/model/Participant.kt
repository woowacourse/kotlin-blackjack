package blackjack.model

import blackjack.state.State

abstract class Participant(
    private val name: String,
    private val blackJack: BlackJack = BlackJack()
) {

    fun draw(card: Card) {
        blackJack.addCard(card)
    }

    fun checkHitState(): Boolean {
        return blackJack.state == State.Action.Hit
    }

    fun getName(): String {
        return name
    }

    fun getCards(): Set<Card> {
        return blackJack.getCards()
    }
}
