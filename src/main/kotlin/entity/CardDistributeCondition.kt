package entity

class CardDistributeCondition(val value: String) {
    init {
        require(value == "y" || value == "n") { CONDITION_ERROR_MESSAGE }
    }

    fun toBoolean(): Boolean {
        return value == "y"
    }

    companion object {
        private const val CONDITION_ERROR_MESSAGE = "y나 n을 입력하여야 합니다"
    }
}
