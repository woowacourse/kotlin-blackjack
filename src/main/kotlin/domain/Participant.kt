package domain

abstract class Participant(val name: Name, val cards: Cards) {
    abstract fun showInitCards(): List<Card>
    abstract fun isPossibleDrawCard(): Boolean
    fun getScore(): Score {
        return cards.getScore()
    }

    fun getGameResult(opponentScore: Score): GameResultType {
        val myScore = getScore()
        if (opponentScore.isBurst() && myScore.isBurst()) return GameResultType.DRAW
        if (opponentScore.isBurst() && !myScore.isBurst()) return GameResultType.WIN
        if (!opponentScore.isBurst() && myScore.isBurst()) return GameResultType.LOSE
        if (opponentScore.getValue() < myScore.getValue()) return GameResultType.WIN
        if (opponentScore.getValue() == myScore.getValue()) return GameResultType.DRAW
        return GameResultType.LOSE
    }
}
