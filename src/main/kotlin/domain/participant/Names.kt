package domain.participant

class Names(val values: List<Name>) {
    init {
        check(values.size <= MAXIMUM_NAME_COUNT) { MAXIMUM_NAME_COUNT_ERROR }
    }

    companion object {
        private const val MAXIMUM_NAME_COUNT = 8
        private const val MAXIMUM_NAME_COUNT_ERROR = "[ERROR] 이름은 최대 8개 까지 허용됩니다."
    }
}
