package domain.state

import domain.card.Card
import domain.card.Cards
import domain.participant.BettingMoney

interface State {
    fun cards(): Cards
    fun bettingMoney(): BettingMoney
    fun draw(card: Card): State

    fun stay(): State

    fun isFinished(): Boolean

    fun getProfitRate(dealerState: State): Double
}
