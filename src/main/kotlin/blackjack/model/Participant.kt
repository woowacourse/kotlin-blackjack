package blackjack.model

import blackjack.state.State

abstract class Participant(
    private val name: String,
    private val blackJack: BlackJack = BlackJack(),
) {
    fun initDraw(cardDeck: CardDeck) {
        repeat(INIT_HAND_CARD_COUNT) {
            blackJack.addCard(cardDeck.draw())
        }
    }

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

    fun judgeBlackState(otherParticipant: Participant): Result {
        return when (blackJack.state) {
            is State.Finish.Bust -> Result.LOSE
            is State.Finish.BlackJack -> applyBlackJackState(otherParticipant)
            is State.Finish.Stay, State.Action.Hit ->
                judgeBlackJackScore(otherParticipant)
        }
    }

    private fun applyBlackJackState(otherParticipant: Participant): Result {
        return if (otherParticipant.getBlackJackState() == State.Finish.BlackJack) {
            Result.DRAW
        } else {
            Result.WIN
        }
    }

    private fun judgeBlackJackScore(otherParticipant: Participant): Result {
        val myScore = this.getBlackJackScore()
        val otherScore = otherParticipant.getBlackJackScore()

        return when {
            otherScore >= BlackJack.BUST_SCORE -> Result.WIN
            myScore > otherScore -> Result.WIN
            myScore < otherScore -> Result.LOSE
            else -> Result.DRAW
        }
    }

    fun calculateProfit(
        player: Player,
        result: Result,
    ): Double {
        val rate = calculateEarningRate(result, blackJack.state)
        val profit = player.getMoney() * rate
        return profit
    }

    private fun calculateEarningRate(
        result: Result,
        state: State,
    ): Double {
        return state.calculateEarningRate(result)
    }

    companion object {
        const val INIT_HAND_CARD_COUNT: Int = 2
    }
}
