package domain

class Player(nameAndBet: NameAndBet, cards: Cards) : Participant(nameAndBet.name, cards) {
    val betMoney = nameAndBet.betMoney
    override fun showInitCards(): List<Card> {
        return cards.cards.take(TAKE_TWO)
    }

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

    companion object {
        private const val TAKE_TWO = 2
    }
}
