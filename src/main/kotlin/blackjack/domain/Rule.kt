package blackjack.domain

class Rule {
    fun calculateScore(hand: Hand): Int {
        val sum = hand.cards.sumOf { it.getNumber() }
        if (hasAce(hand) && (sum + ACE_VALUE_DIFFERENCE <= BLACKJACK_SCORE)) {
            return sum + ACE_VALUE_DIFFERENCE
        }
        return sum
    }

    fun isBust(hand: Hand): Boolean = calculateScore(hand) > BLACKJACK_SCORE

    fun getResult(
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

    companion object {
        private const val BLACKJACK_SCORE = 21
        private const val ACE_VALUE_DIFFERENCE = 10
        private const val BLACKJACK_CONDITION = 2
    }
}
