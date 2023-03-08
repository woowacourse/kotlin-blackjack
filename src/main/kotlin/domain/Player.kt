package domain

import domain.card.Cards
import domain.result.GameResult

class Player(nameAndBet: NameAndBet, cards: Cards) : Participant(nameAndBet.name, cards) {
    private val betMoney = nameAndBet.betMoney
    override fun isPossibleDrawCard(): Boolean = !isBurst()

    fun getGameResult(dealer: Dealer): GameResult {
        return when {
            isBurst() -> GameResult.LOSE
            isBlackJack() && dealer.isBlackJack() -> GameResult.DRAW
            isBlackJack() -> GameResult.WIN_BLACKJACK
            dealer.isBurst() || resultSum() > dealer.resultSum() -> GameResult.WIN
            resultSum() == dealer.resultSum() -> GameResult.DRAW
            else -> GameResult.LOSE
        }
    }

    fun getGameProfitMoney(dealer: Dealer): Int {
        return (getGameResult(dealer).profitRate * betMoney).toInt()
    }
}
