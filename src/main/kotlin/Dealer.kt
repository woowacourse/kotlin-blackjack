class Dealer(val cards: Cards = Cards()) {

    fun isPossibleToDraw(): Boolean {
        if (cards.getTotalCardsValue() >= 17)
            return false

        return true
    }

    fun drawCard() {
        cards.draw()
    }
}
