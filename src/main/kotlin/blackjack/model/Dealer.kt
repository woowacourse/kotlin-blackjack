package blackjack.model

class Dealer(name: String = "딜러") : Participant(name) {
    fun hitOrStay(
        dealingShoe: DealingShoe,
        printDealerHitMessage: () -> Unit,
    ) {
        while (isHittable()) {
            super.pickCard(dealingShoe, 1)
            printDealerHitMessage()
        }
    }

    override fun isHittable(): Boolean {
        return hand.getCardSum() < DEALER_HITTABLE_THRESHOLD
    }

    override fun judge(participant: Participant): Return {
        return when {
            participant.isBusted() -> Return.WIN
            this.isBusted() -> Return.LOSE
            participant.isBlackJack() && !this.isBlackJack() -> Return.LOSE_BLACKJACK
            else -> super.judge(participant)
        }
    }

    override fun calculateBetAmount(vararg participant: Participant): Long {
        val betAmountSum: Long =
            participant.sumOf { player ->
                player.calculateBetAmount(this@Dealer)
            }
        return -betAmountSum
    }

    companion object {
        private const val DEALER_HITTABLE_THRESHOLD = 17
    }
}
