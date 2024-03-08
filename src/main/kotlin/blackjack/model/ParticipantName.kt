package blackjack.model

class ParticipantName(private val name: String) {
    init {
        require(name.isNotEmpty()) { NAME_EMPTY_ERROR_MESSAGE }
    }

    override fun toString(): String = name

    companion object {
        private const val NAME_EMPTY_ERROR_MESSAGE = "이름은 공백이 될 수 없습니다."
    }
}
