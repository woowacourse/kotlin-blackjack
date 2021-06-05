package blackjack.state

import blackjack.domain.card.Card
import blackjack.domain.gamer.Dealer
import blackjack.domain.gamer.Money
import blackjack.domain.gamer.Score
import blackjack.domain.result.GameResult

interface State {
    fun isBust(): Boolean
    fun isBlackjack(): Boolean
    fun draw(card: Card) : State
    fun totalScore(): Score
    fun cards(): List<Card>
    fun stay(): State
    fun isFinish() : Boolean
    fun result(dealerScore: Score): GameResult
    fun earningRate(dealer: Dealer): Double
}