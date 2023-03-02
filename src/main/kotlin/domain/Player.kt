package domain

class Player(name: Name, cards: Cards) : Participant(name, cards) {
    override fun showInitCards(): List<Card> {
        return cards.cards.take(2)
    }

    override fun isPossibleDrawCard(): Boolean {
        return when (cards.minSumState()) {
            is Cards.State.Burst -> false
            is Cards.State.NoBurst -> true
        }
    }

    fun getGameResult(compareState: Cards.State): GameResult {
        val myState = getSumStateResult()
        if (myState is Cards.State.Burst) return GameResult.LOSE
        if (compareState is Cards.State.Burst) return GameResult.WIN
        if (myState.sum > compareState.sum) return GameResult.WIN
        return GameResult.LOSE
    }
}
