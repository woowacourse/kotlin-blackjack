package blackjack.model

import blackjack.state.State

abstract class Participant(
    private val wallet: Wallet,
    private val blackJack: BlackJack = BlackJack(),
) {
    abstract fun openInitCards(): List<Card>

    abstract fun shouldDrawCard(): Boolean

    fun match(other: Participant): Result {
        val myScore = getBlackJackScore()
        val otherScore = other.getBlackJackScore()

        return when {
            this.getGameState() == State.Finish.Bust -> Result.LOSE
            other.getGameState() == State.Finish.Bust -> Result.WIN
            this.isBlackJackState() && other.isBlackJackState() -> Result.DRAW
            this.isBlackJackState() -> Result.WIN
            myScore > otherScore -> Result.WIN
            myScore == otherScore -> Result.DRAW
            else -> Result.LOSE
        }
    }

    fun draw(card: Card) {
        blackJack.addCard(card)
    }

    fun transitionToStayState() {
        blackJack.switchToStayState()
    }

    fun isHitState(): Boolean {
        return blackJack.isDrawState()
    }

    fun isBlackJackState(): Boolean {
        return blackJack.isBlackJackState()
    }

    fun getGameState(): State {
        return blackJack.getState()
    }

    fun getName(): String {
        return wallet.identification.name
    }

    fun getBettingMoney(): Int {
        return wallet.money
    }

    fun getCards(): List<Card> {
        return blackJack.getCards()
    }

    fun getBlackJackScore(): Int {
        return blackJack.getHandCardScore()
    }
}
