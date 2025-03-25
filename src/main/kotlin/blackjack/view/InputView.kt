package blackjack.view

import blackjack.domain.BetAmount
import java.lang.IllegalArgumentException

object InputView {
    fun readPlayerNames(): List<String> =
        runCatching {
            println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
            val input = readln()
            val inputs =
                input.split(",")
                    .map { it.trim() }
                    .filter { it.isNotBlank() }

            if (inputs.isEmpty()) {
                throw IllegalArgumentException("이름은 공백이 불가합니다")
            }
            return inputs
        }.getOrElse {
            println(it.message)
            readPlayerNames()
        }

    fun readBetAmounts(name: String): BetAmount =
        runCatching {
            println("${name}의 배팅 금액은?")
            val input = readln().toInt()
            BetAmount(input)
        }.getOrElse {
            println(it.message)
            readBetAmounts(name)
        }

    fun askPlayerHit(name: String): Boolean =
        runCatching {
            println("${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
            val input = readlnOrNull()
            when (input) {
                "y" -> true
                "n" -> false
                else -> throw IllegalArgumentException("올바른 답변을 입력해주세요")
            }
        }.getOrElse {
            println(it.message)
            askPlayerHit(name)
        }
}
