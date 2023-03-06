package blackjack.domain

class Player(val name: String, override val cardBunch: CardBunch) : Participant {

    fun chooseWinner(dealer: Dealer): Consequence = dealer.compareScore(cardBunch.getTotalScore())

    fun isBurst() = cardBunch.isBurst()
}
