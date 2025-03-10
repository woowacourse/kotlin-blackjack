package model

class CardDistributor(private var deck: Cards) {
    fun distributeInitialCards(): Hand {
        val initialCards = deck.allCards.take(2)
        deck = Cards(deck.allCards - initialCards)
        return Hand(initialCards)
    }

    fun drawCard(): Card {
        val drawnCard = deck.allCards.first()
        deck = Cards(deck.allCards - drawnCard)
        return drawnCard
    }
}
