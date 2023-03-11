package domain.participants

import domain.card.Cards

class Player(val name: String, val ownCards: Cards, val bettingMoney: Int) {

    fun getWinningResult(dealer: Dealer) = dealer.judgePlayerResult(ownCards)
}
