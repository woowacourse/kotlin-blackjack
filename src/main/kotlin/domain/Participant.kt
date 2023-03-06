package domain

abstract class Participant(val name: Name, val cards: Cards) {
    abstract fun showInitCards(): List<Card>
    abstract fun isPossibleDrawCard(): Boolean
    fun getScore(): Score {
        return cards.getScore()
    }

    fun getGameResult(opponentScore: Score): GameResultType {
        val myScore = getScore()
        if (opponentScore.isBurst() && myScore.isBurst()) return GameResultType.무
        if (opponentScore.isBurst() && !myScore.isBurst()) return GameResultType.승
        if (!opponentScore.isBurst() && myScore.isBurst()) return GameResultType.패
        if (opponentScore.getValue() < myScore.getValue()) return GameResultType.승
        if (opponentScore.getValue() == myScore.getValue()) return GameResultType.무
        return GameResultType.패
    }
}
