package blackjack.model

class Participants(private val participants: List<Participant>) {
    init {
        require(participants.size <= MAX_PARTICIPANTS_SIZE) { ERROR_MAX_PARTICIPANTS_SIZE }
    }

    fun getAliveParticipants(): List<Participant> {
        return participants.filter {
            it.checkHitState()
        }
    }

    fun getParticipants(): List<Participant> {
        return participants
    }

    companion object {
        private const val MAX_PARTICIPANTS_SIZE: Int = 8
        private const val ERROR_MAX_PARTICIPANTS_SIZE = "게임 참가자의 수는 ${MAX_PARTICIPANTS_SIZE}을 초과할 수 없습니다."
    }
}
