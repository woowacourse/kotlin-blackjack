package blackjack.domain.card

class CardDeck {

    private val cards = RandomCardsGenerator().generate()

    fun checkProvidePossible(): Boolean {
        if (cards.isNotEmpty()) return true
        return false
    }
}
