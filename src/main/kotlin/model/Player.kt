package model

class Player(cards: Cards, name: Name) : Participant(cards, name) {
    override fun getFirstOpenCards(): Cards = cards

    override fun isPossibleDrawCard(): Boolean = !isBust()
    override fun getGameResult(other: Participant): Result {
        if (isBust()) return Result.LOSE
        if (other.isBust()) return Result.WIN
        if (cards.sum() > other.cards.sum()) return Result.WIN
        return Result.LOSE
    }

    companion object {
        fun from(name: String): Player = Player(Cards(setOf()), Name(name))
    }
}
