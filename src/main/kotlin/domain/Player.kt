package domain

class Player(name: Name, cards: Cards) : Participant(name, cards) {
    override fun showInitCards(): List<Card> {
        return cards.cards.take(TAKE_TWO)
    }

    override fun isPossibleDrawCard(): Boolean = !isBurst()

    fun getGameResult(participant: Participant): GameResult {
        if (isBurst()) return GameResult.LOSE
        if (participant.isBurst()) return GameResult.WIN
        if (isBlackJack() && participant.isBlackJack()) return GameResult.DRAW
        if (participant.isBlackJack()) return GameResult.LOSE
        if (isBlackJack()) return GameResult.WIN
        if (resultSum() > participant.resultSum()) return GameResult.WIN
        if (resultSum() == participant.resultSum()) return GameResult.DRAW
        return GameResult.LOSE
    }

    companion object {
        private const val TAKE_TWO = 2
    }
}
