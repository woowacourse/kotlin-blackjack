package entity

class CardDistributeCondition(val value: String) {
    init {
        require(value == CONDITION_YES_MESSAGE || value == CONDITION_NO_MESSAGE) { CONDITION_ERROR_MESSAGE }
    }

    fun toBoolean(): Boolean {
        return value == CONDITION_YES_MESSAGE
    }

    companion object {
        private const val CONDITION_YES_MESSAGE = "y"
        private const val CONDITION_NO_MESSAGE = "n"
        private const val CONDITION_ERROR_MESSAGE = "y나 n을 입력하여야 합니다."
    }
}
