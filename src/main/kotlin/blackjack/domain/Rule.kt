package blackjack.domain

object Rule {
    const val BLACKJACK_SCORE = 21
    const val INITIAL_CARD_COUNT = 2
    private const val ACE_BONUS_SCORE = 10
    private const val BLACKJACK_CONDITION = 2

    fun calculateScore(hand: Hand): Int {
        val sum = hand.cards.sumOf { it.getNumber() }
        if (hasAce(hand) && (sum + ACE_BONUS_SCORE <= BLACKJACK_SCORE)) {
            return sum + ACE_BONUS_SCORE
        }
        return sum
    }

    fun isBust(hand: Hand): Boolean = calculateScore(hand) > BLACKJACK_SCORE

    fun getPlayerResult(
        dealer: Dealer,
        player: Player,
    ): Result {
        return when {
            isBlackjack(dealer.hand) && isBlackjack(player.hand) -> Result.PUSH
            isBlackjack(dealer.hand) -> Result.LOSE
            isBlackjack(player.hand) -> Result.WIN
            isBust(player.hand) && isBust(dealer.hand) -> Result.LOSE
            isBust(player.hand) -> Result.LOSE
            isBust(dealer.hand) -> Result.WIN
            else -> compareScores(dealer, player)
        }
    }

    fun getDealerResult(playerResult: Map<Player, Result>): Map<Result, Int> =
        playerResult.values
            .groupingBy {
                when (it) {
                    Result.WIN -> Result.LOSE
                    Result.LOSE -> Result.WIN
                    Result.PUSH -> Result.PUSH
                }
            }.eachCount()
            .withDefault { 0 }

    private fun compareScores(
        dealer: Dealer,
        player: Player,
    ): Result {
        return when {
            calculateScore(player.hand) > calculateScore(dealer.hand) -> Result.WIN
            calculateScore(player.hand) < calculateScore(dealer.hand) -> Result.LOSE
            else -> Result.PUSH
        }
    }

    private fun isBlackjack(hand: Hand): Boolean {
        return hand.cards.size == BLACKJACK_CONDITION && calculateScore(hand) == BLACKJACK_SCORE
    }

    private fun hasAce(hand: Hand): Boolean = hand.cards.any { it.isAce() }
}
