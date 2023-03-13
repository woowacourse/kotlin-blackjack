package domain

class Names(val values: List<Name>) {
    init {
        check(values.size in MINIMUM_NAME_COUNT..MAXIMUM_NAME_COUNT) { MAXIMUM_NAME_COUNT_ERROR }
    }

    companion object {
        private const val MINIMUM_NAME_COUNT = 1
        private const val MAXIMUM_NAME_COUNT = 8
        private const val MAXIMUM_NAME_COUNT_ERROR = "[ERROR] 이름은 최대 1개에서 8개까지 허용됩니다."
    }
}
