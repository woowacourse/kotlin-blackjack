package blackjack.domain.player

class Dealer(name: String) : Player(name) {

    fun checkMustGenerateCard(): Boolean {
        if (cards.sumCardsNumber() <= MIN_SUM_NUMBER) return true
        return false
    }

    companion object {
        const val MIN_SUM_NUMBER = 16
    }
}
