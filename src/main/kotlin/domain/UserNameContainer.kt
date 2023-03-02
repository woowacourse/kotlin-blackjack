package domain

class UserNameContainer(val names: List<String>) {

    init {
        exceptDuplicatedName()
    }

    private fun exceptDuplicatedName() {
        require(names.toSet().size == names.size) {
            ERROR_DUPLICATE_USER_NAME
        }
    }

    companion object {
        private const val ERROR_DUPLICATE_USER_NAME = "[ERROR] 유저의 이름이 중복되었습니다."
    }
}
