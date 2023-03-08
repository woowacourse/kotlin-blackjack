package model

class Player(hand: Hand, name: Name) : Participant(hand, name) {
    override fun isHit(): Boolean = !isBust() && (hand.sum() != BLACKJACK_POINT)

    private fun isBlackJackWin(dealer: Dealer): Boolean {
        return when {
            !dealer.isBlackJack() && isBlackJack() -> true
            else -> false
        }
    }

    private fun isWin(dealer: Dealer): Boolean {
        return when {
            !isBust() && dealer.isBust() -> true
            !dealer.isBlackJack() && isBlackJack() -> true
            !(isBlackJack() || dealer.isBlackJack()) && (hand.sum() > dealer.hand.sum()) -> true
            else -> false
        }
    }

    private fun isPush(dealer: Dealer): Boolean {
        return when {
            isBlackJack() && dealer.isBlackJack() -> true
            !(isBlackJack() || dealer.isBlackJack()) && (hand.sum() == dealer.hand.sum()) -> true
            else -> false
        }
    }

    private fun isLose(dealer: Dealer): Boolean {
        return when {
            isBust() -> true
            !isBlackJack() && dealer.isBlackJack() -> true
            !dealer.isBust() && !(isBlackJack() || dealer.isBlackJack()) && (hand.sum() < dealer.hand.sum()) -> true
            else -> false
        }
    }

    fun judge(dealer: Dealer): FinalResult {
        return when {
            isBlackJackWin(dealer) -> FinalResult.BLACKJACK_WIN
            isLose(dealer) -> FinalResult.LOSE
            isWin(dealer) -> FinalResult.WIN
            isPush(dealer) -> FinalResult.PUSH
            else -> FinalResult.LOSE
        }
    }
}
