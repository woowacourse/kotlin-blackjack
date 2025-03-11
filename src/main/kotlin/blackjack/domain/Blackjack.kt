package blackjack.domain

class Blackjack(
    private val dealer: Dealer,
) {
    fun dealCards() {
        dealer.draw()
        dealer.pitch()
        dealer.pitch()
    }

    fun startPlayerTurn(turn: (Player) -> Unit) {
        dealer.startPlayerTurn(turn)
    }

    fun startDealerTurn() {
        dealer.startTurn()
    }

    fun setResult() {
        dealer.setResult()
    }
}
