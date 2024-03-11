package blackjack.model

import blackjack.state.State

abstract class Participant(
    private val name: String,
    private val blackJack: BlackJack = BlackJack(),
) {
    fun draw(card: Card) {
        blackJack.addCard(card)
    }

    fun checkHitState(): Boolean {
        return blackJack.checkDrawState()
    }

    fun getBlackJackState(): State {
        return blackJack.state
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

    companion object {
        const val INIT_HAND_CARD_COUNT: Int = 2
    }
}
