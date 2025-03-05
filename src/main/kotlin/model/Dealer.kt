package model

class Dealer(val cards: Cards) {
    init {
        require(cards.getCardsCount() == 2)
    }
}
