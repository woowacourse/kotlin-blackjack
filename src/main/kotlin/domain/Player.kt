package domain

class Player(name: Name, cards: Cards) : Participant(name, cards) {
    override fun showInitCards(): List<Card> {
        return cards.list.take(TAKE_TWO)
    }

    override fun isPossibleDrawCard(): Boolean {
       return !cards.getScore().isBurst()
    }

    companion object {
        private const val TAKE_TWO = 2
    }
}
