package blackjack.model

class Participants(private val participants: List<Participant>) {
    init {
        require(participants.size <= MAX_PARTICIPANTS_SIZE) {
            ERROR_MAX_PARTICIPANTS_SIZE
        }
        require(participants.isNotEmpty()) {
            ERROR_NOT_EXIST_PARTICIPANT
        }
        require(participants.first() is Dealer) {
            ERROR_NOT_EXIST_DEALER
        }
        require(participants.count { it is Dealer } == DEALER_COUNT) {
            ERROR_INVALID_DEALER_COUNT
        }
        require(participants.size >= MIN_PARTICIPANTS_SIZE) {
            ERROR_NOT_EXIST_PLAYERS
        }
    }

    fun getAlivePlayers(): List<Participant> {
        return participants.filter { participant ->
            participant.checkHitState() && participant !is Dealer
        }
    }

    fun getParticipants(): List<Participant> {
        return participants
    }

    fun getDealer(): Dealer {
        return participants.first() as Dealer
    }

    fun getPlayers(): List<Player> {
        return participants.subList(FIRST_PLAYER_INDEX, participants.size).map { it as Player }
    }

    fun makeProfitResult(playersProfit: List<Double>): List<Double> {
        val dealerProfit = -playersProfit.sum()
        val participantProfitResult = mutableListOf(dealerProfit)
        participantProfitResult.addAll(playersProfit)
        return participantProfitResult
    }

    companion object {
        private const val MAX_PARTICIPANTS_SIZE: Int = 8
        private const val MIN_PARTICIPANTS_SIZE: Int = 2
        private const val DEALER_COUNT: Int = 1
        private const val FIRST_PLAYER_INDEX: Int = 1
        private const val ERROR_MAX_PARTICIPANTS_SIZE = "게임 참가자의 수는 ${MAX_PARTICIPANTS_SIZE}을 초과할 수 없습니다."
        private const val ERROR_NOT_EXIST_PARTICIPANT = "참가자가 없습니다."
        private const val ERROR_NOT_EXIST_DEALER = "딜러가 없습니다."
        private const val ERROR_NOT_EXIST_PLAYERS = "플레이어가 없습니다."
        private const val ERROR_INVALID_DEALER_COUNT = "딜러는 1명만 존재해야 합니다."
    }
}
