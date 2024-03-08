package blackjack.model

data class Participants(val participants: List<Role>) {
    init {
        require(participants.size in MIN_PARTICIPANTS_COUNT..MAX_PARTICIPANTS_COUNT) {
            "딜러를 포함한 참가자의 수가 2명 이상, 7명 이하여야 합니다."
        }
    }

    fun addInitialCards() {
        participants.forEach {
            it.addInitialCards()
        }
    }

    fun getDealerSum(): Int = participants.filterIsInstance<Dealer>()[0].cardHand.sum()

    fun getPlayerResult(): Map<PlayerName, Int> =
        participants.filterIsInstance<Player>().associate {
            it.name to it.cardHand.sum()
        }

    companion object {
        private const val MIN_PARTICIPANTS_COUNT = 2
        private const val MAX_PARTICIPANTS_COUNT = 7
    }
}
