package model

class Player(cards: Cards, name: Name) : Participant(cards, name) {
    override fun isPossibleDrawCard(): Boolean = !isBust()

    fun getGameResult(other: Participant): Result {
        if (isBust()) return Result.LOSE
        if (other.isBust()) return Result.WIN
        if (cards.sum() > other.cards.sum()) return Result.WIN
        return Result.LOSE
    }
}
