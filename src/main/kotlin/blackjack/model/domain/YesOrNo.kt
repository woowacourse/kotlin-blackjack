package blackjack.model.domain

class YesOrNo(val input: String) {
    init {
        require(input in listOf("y", "n", "Y", "N")) { "잘못된 입력입니다." }
    }
}
