package blackjack.model

sealed class ParticipantInfo(open val name: ParticipantName)

data class PlayerInfo(override val name: ParticipantName, val betAmount: ParticipantBetAmount) : ParticipantInfo(name)

data class DealerInfo(override val name: ParticipantName = ParticipantName(DEALER_NAME)) : ParticipantInfo(name) {
    companion object {
        private const val DEALER_NAME = "딜러"
    }
}
