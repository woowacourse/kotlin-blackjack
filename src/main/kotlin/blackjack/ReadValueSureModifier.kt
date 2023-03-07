package blackjack

import blackjack.view.ResultView

object ReadValueSureModifier {

    fun <T, E> tryToReadValueAndModifyToTargetUntilNoErrorOccur(
        readValue: () -> T?,
        modifyToTarget: (T) -> E,
    ): E {
        while (true) {
            runCatching { return modifyToTarget(executeUntilGetCorrectType(readValue)) }
                .onFailure { ResultView.printErrorMessage(it) }
        }
    }

    private fun <T> executeUntilGetCorrectType(getValue: () -> T?): T {
        var value = getValue()
        while (value == null) {
            ResultView.printMessage("입력한 값이 올바른 타입이 아닙니다.")
            value = getValue()
        }
        return value
    }
}
