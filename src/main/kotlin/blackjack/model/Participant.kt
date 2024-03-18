package blackjack.model

abstract class Participant(val name: String) {
    protected val hand = Hand()

    fun pickCard(
        dealingShoe: DealingShoe,
        repeatSize: Int = 1,
    ) {
        hand.pickCard(dealingShoe, repeatSize)
    }

    fun showCard(): List<Card> = hand.showCard()

    fun isBusted(): Boolean = hand.isBusted()

    fun isBlackJack(): Boolean = hand.isBlackJack()

    fun getCardSum(): Int = hand.getCardSum()

    abstract fun isHittable(): Boolean

    open fun judge(participant: Participant): GameResult {
        return when {
            participant.isBlackJack() && this.isBlackJack() -> GameResult.DRAW
            else -> compareScore(participant.hand.getCardSum())
        }
    }

    private fun compareScore(compareScore: Int): GameResult {
        val myCardSum = hand.getCardSum()
        return when {
            (compareScore < myCardSum) -> GameResult.WIN
            (compareScore > myCardSum) -> GameResult.LOSE
            else -> GameResult.DRAW
        }
    }
}
