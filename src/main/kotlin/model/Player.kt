package model

class Player(name: Name) : Participant(name) {
    override fun getFirstOpenCards(): Cards = cards

    override fun isPossibleDrawCard(): Boolean = !isBust()

    override fun isHit(needToDraw: (String) -> Boolean): Boolean {
        return isPossibleDrawCard() && needToDraw(name.value)
    }

    companion object {
        fun from(name: String): Player = Player(Name(name))
    }
}
