package domain.participants

import domain.card.Cards

class Player(val name: String, val ownCards: Cards, val bettingMoney: Money) {

    fun getWinningResult(dealer: Dealer) = dealer.judgePlayerResult(ownCards)
}
