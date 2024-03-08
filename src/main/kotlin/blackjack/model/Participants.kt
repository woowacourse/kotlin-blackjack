package blackjack.model

data class Participants(val participants: List<Role>) {
    init {
        require(participants.size in PARTICIPANTS_COUNT_RANGE) {
            "딜러를 포함한 참가자의 수가 ${MIN_PARTICIPANTS_COUNT}명 이상, ${MAX_PARTICIPANTS_COUNT}명 이하여야 합니다."
        }
    }

    fun addInitialCards() {
        participants.forEach {
            it.addInitialCards()
        }
    }

    fun getDealerSum(): Int = participants.filterIsInstance<Dealer>().first().cardHand.sum()

    fun getPlayerResult(): Map<PlayerName, Int> =
        participants.filterIsInstance<Player>().associate {
            it.name to it.cardHand.sum()
        }

    companion object {
        private const val MIN_PARTICIPANTS_COUNT = 2
        private const val MAX_PARTICIPANTS_COUNT = 7
        private val PARTICIPANTS_COUNT_RANGE = MIN_PARTICIPANTS_COUNT..MAX_PARTICIPANTS_COUNT
    }
}
