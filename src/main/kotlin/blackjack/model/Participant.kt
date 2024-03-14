package blackjack.model

import blackjack.state.State

abstract class Participant(
    private val name: String,
    private val blackJack: BlackJack = BlackJack(),
) {
    init {
        require(name.length <= MAX_NAME_LENGTH) {
            ERROR_NAME_LENGTH
        }
    }

    abstract fun openInitCards(): List<Card>

    abstract fun checkShouldDrawCard(): Boolean

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

    fun getCards(): List<Card> {
        return blackJack.getCards()
    }

    fun getBlackJackScore(): Int {
        return blackJack.getHandCardScore()
    }

    companion object {
        private const val MAX_NAME_LENGTH = 8
        private const val ERROR_NAME_LENGTH = "사용자 이름은 최대 ${MAX_NAME_LENGTH}자 입니다."
    }
}
