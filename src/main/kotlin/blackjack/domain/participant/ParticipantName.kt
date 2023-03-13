package blackjack.domain.participant

data class ParticipantName(private val value: String) {

    init {
        require(value.matches(NAME_REGEX)) { PARTICIPANT_NAME_LENGTH_ERROR.format(value) }
    }

    override fun toString(): String = value

    companion object {
        private const val MIN_NAME_LENGTH = 2
        private const val MAX_NAME_LENGTH = 20
        private val NAME_REGEX = Regex("[가-힣a-zA-Z]{$MIN_NAME_LENGTH,$MAX_NAME_LENGTH}")
        private const val PARTICIPANT_NAME_LENGTH_ERROR = "게임 참여자의 이름은 2자 이상 20자 이하의 한글 또는 영문으로 이루어져야 합니다.\n잘못된 값: %s"
    }
}
