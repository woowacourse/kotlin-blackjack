package model

class Player(cards: Cards, name: Name) : Participant(cards, name) {
    override fun getFirstOpenCards(): Cards = cards

    override fun isPossibleDrawCard(): Boolean = !isBust()

    override fun isHit(needToDraw: (String) -> Boolean): Boolean {
        return isPossibleDrawCard() && needToDraw(name.value)
    }

    companion object {
        fun from(name: String): Player = Player(Cards(setOf()), Name(name))
    }
}
