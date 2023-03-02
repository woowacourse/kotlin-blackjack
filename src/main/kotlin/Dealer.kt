class Dealer(val cards: Cards = Cards()) {

    fun isPossibleToDraw(): Boolean {
        if (cards.getSum() >= 17)
            return false

        return true
    }

    fun drawCard() {
        if (isPossibleToDraw()) {
            cards.draw()
        }
    }
}
