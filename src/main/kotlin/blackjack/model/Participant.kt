package blackjack.model

import blackjack.state.State

abstract class Participant(
    private val name: String,
    private val blackJack: BlackJack = BlackJack(),
) {
    abstract fun openInitCards(): List<Card>?

    fun draw(card: Card) {
        blackJack.addCard(card)
    }

    fun transitionToStayState() {
        blackJack.switchToStayState()
    }

    fun checkHitState(): Boolean {
        return blackJack.checkDrawState()
    }

    fun getBlackJackState(): State {
        return blackJack.getBlackJackState()
    }

    fun getName(): String {
        return name
    }

    fun getCards(): Set<Card> {
        return blackJack.getCards()
    }

    fun getBlackJackScore(): Int {
        return blackJack.getHandCardScore()
    }
}
