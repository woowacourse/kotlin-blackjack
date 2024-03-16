package blackjack.model

abstract class Participant(val name: String) {
    val hand = Hand()

    fun pickCard(
        dealingShoe: DealingShoe,
        repeatSize: Int = 1,
    ) {
        hand.pickCard(dealingShoe, repeatSize)
    }

    open fun judge(participant: Participant): Return {
        return when {
            participant.isBlackJack() && this.isBlackJack() -> Return.DRAW
            else -> compareScore(participant.hand.getCardSum())
        }
    }

    private fun compareScore(compareScore: Int): Return {
        val myCardSum = hand.getCardSum()
        return when {
            (compareScore < myCardSum) -> Return.WIN
            (compareScore > myCardSum) -> Return.LOSE
            else -> Return.DRAW
        }
    }

    fun showCard(): List<Card> = hand.showCard()

    fun isBusted(): Boolean = hand.isBusted()

    fun isMaxScore(): Boolean = hand.isMaxScore()

    fun isBlackJack(): Boolean = hand.isBlackJack()

    fun getCardSum(): Int = hand.getCardSum()

    abstract fun isHittable(): Boolean

    abstract fun calculateBetAmount(vararg participant: Participant): Long
}
