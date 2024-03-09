package blackjack.model

class ParticipantName(private val name: String) {
    init {
        require(name.isNotEmpty()) { EMPTY_NAME_ERROR_MESSAGE }
    }

    override fun toString(): String = name

    companion object {
        private const val EMPTY_NAME_ERROR_MESSAGE = "[ERROR] 이름은 공백이 될 수 없습니다."
    }
}
