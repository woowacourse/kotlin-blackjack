package blackjack.model

sealed class ParticipantInfo(open val name: ParticipantName) {
    abstract fun isDealer(): Boolean

    abstract fun getBetAmount(): Int
}

class PlayerInfo(override val name: ParticipantName, private val betAmount: ParticipantBetAmount) :
    ParticipantInfo(name) {
    override fun isDealer() = false

    override fun getBetAmount() = betAmount.getAmount()
}

class DealerInfo(override val name: ParticipantName = ParticipantName(DEALER_NAME)) : ParticipantInfo(name) {
    override fun isDealer() = true

    override fun getBetAmount() = 0

    companion object {
        private const val DEALER_NAME = "딜러"
    }
}
