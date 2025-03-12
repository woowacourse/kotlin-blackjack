package blackjack.view.blackjackView

object BlackJackInputView {
    fun askPlayerHit(name: String): Boolean {
        println(requestAdditionalCard(name))

        return retryUntilValidInput {
            val input = readln()
            when (input) {
                YES -> true
                NO -> false
                else -> null
            }
        }
    }

    private fun <T> retryUntilValidInput(action: () -> T?): T {
        var tried = 0
        var result = action()
        while (result == null && tried < RETRY_COUNT) {
            println(ERR_REQUEST_RETRY)
            result = action()
            tried++
        }
        requireNotNull(result) { ERR_INVALID_FORMAT }
        return result
    }

    private fun requestAdditionalCard(name: String): String = "${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"

    private const val YES = "y"
    private const val NO = "n"
    private const val RETRY_COUNT = 3
    private const val ERR_INVALID_FORMAT = "$RETRY_COUNT 번 이상 잘못된 값을 입력하였습니다"
    private const val ERR_REQUEST_RETRY = "올바르지 않은 값입니다 다시 시도해주세요"
}
