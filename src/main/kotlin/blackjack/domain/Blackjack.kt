package blackjack.domain

class Blackjack(
    private val dealer: Dealer,
) {
    fun dealCards() {
        dealer.draw()
        dealer.pitch()
        dealer.pitch()
    }

    fun startPlayerTurn(
        onStart: (Player) -> Unit,
        wantToHit: (Player) -> Boolean,
        afterHit: (Player) -> Unit,
    ) {
        dealer.startPlayerTurn(onStart, wantToHit, afterHit)
    }

    fun startDealerTurn(onEachTurn: () -> Unit) {
        dealer.startDealerTurn(onEachTurn)
    }

    fun setResult() {
        dealer.setResult()
    }
}
