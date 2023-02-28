class Dealer(val cards: Cards) {

    fun drawCard() {
        if (cards.isPossibleToDraw(17)) {
            cards.draw()
        }
    }
}
