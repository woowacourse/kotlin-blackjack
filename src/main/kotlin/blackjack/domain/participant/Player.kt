package blackjack.domain.participant

import blackjack.domain.Money
import blackjack.domain.ResultType
import blackjack.domain.card.Hand
import blackjack.domain.state.Betting
import blackjack.domain.state.State

class Player(name: ParticipantName) : Participant(name) {

    override var state: State = Betting(Hand(listOf()))
    val bettingMoney: Money?
        get() = state.bettingMoney

    constructor(name: String) : this(ParticipantName(name))

    fun betting(money: Money) {
        state = state.betting(money)
    }

    fun stay() {
        state = state.stay()
    }

    infix fun against(dealer: Dealer): ResultType {
        if (dealer.isBust()) return ResultType.WIN
        if (isBust()) return ResultType.LOSE

        val score = this.getScore()
        val dealerScore = dealer.getScore()
        return when {
            score > dealerScore -> ResultType.WIN
            score == dealerScore -> ResultType.TIE
            else -> ResultType.LOSE
        }
    }

    fun getProfit(): Double = state.getProfit()

    override fun equals(other: Any?): Boolean = if (other is Player) name == other.name else false
    override fun hashCode(): Int = this.name.hashCode()
}
