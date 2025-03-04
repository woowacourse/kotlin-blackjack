package blackjack.domain.model

data class Dealer(val name: String = DEALER_NAME, val cards: List<Card> = emptyList()) {
    fun accept(card: Card): Dealer {
        return this.copy(cards = cards + card)
    }

    fun getScore(): Int {
        val score = cards.sumOf { it.rank.score }
        return score + getBonusScore(totalScore = score)
    }

    private fun getBonusScore(totalScore: Int): Int {
        if (totalScore <= MAX_BONUS_SCORE && hasAce()) return BONUS_SCORE
        return 0
    }

    private fun hasAce() = cards.any { it.rank == Rank.ACE }

    fun getPlayerVerdict(players: List<Player>): Map<Player, Verdict> {
        val dealerScore = getScore()
        return players.associateWith { player -> Verdict.determine(player.getScore(), dealerScore) }
    }

    fun getDealerVerdicts(players: List<Player>): List<Verdict> {
        val dealerScore = getScore()
        return players.map { player -> Verdict.determine(dealerScore, player.getScore()) }
    }

    private companion object {
        const val DEALER_NAME = "딜러"
        const val MAX_BONUS_SCORE = 11
        const val BONUS_SCORE = 10
    }
}
