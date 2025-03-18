package blackjack.view

import blackjack.model.EventProvider

class InputView : EventProvider {
    override fun getNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readln().split(",").map { name -> name.trim() }
    }

    override fun getIsDrawMore(name: String): Boolean {
        println("\n${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return getIsDrawMoreInput()
    }

    override fun getBetAmount(name: String): Int {
        println("${name}의 배팅 금액은?")
        return getBetAmountInput()
    }

    private tailrec fun getIsDrawMoreInput(): Boolean {
        val value = readln().lowercase()
        if (value != "y" && value != "n") {
            println("[ERROR] 잘못된 입력값입니다.")
        } else {
            return value == "y"
        }
        return getIsDrawMoreInput()
    }

    private tailrec fun getBetAmountInput(): Int {
        val value = readln().toIntOrNull()
        if (value == null) {
            println("[ERROR] 정수가 아닌 입력입니다.")
        } else if (value < 0) {
            println("[ERROR] 음수는 입력할 수 없습니다.")
        } else {
            return value
        }
        return getBetAmountInput()
    }

    fun moreGame(): Boolean {
        println("\n한판 더 하시겠습니까?")
        return moreGameInput()
    }

    private tailrec fun moreGameInput(): Boolean {
        val value = readln().lowercase()
        if (value != "y" && value != "n") {
            println("[ERROR] 잘못된 입력값입니다.")
        } else {
            return value == "y"
        }
        return moreGameInput()
    }
}
