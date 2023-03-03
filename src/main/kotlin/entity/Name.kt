package entity

class Name(val value: String) {
    init {
        require(value.isEmpty()) { NAME_ERROR_MESSAGE }
    }

    companion object {
        private const val NAME_ERROR_MESSAGE = "이름음 1글자 이상 작성되어야 합니다."
    }
}
