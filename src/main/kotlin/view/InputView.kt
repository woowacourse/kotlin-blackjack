package view

import domain.player.Money
import dto.PlayerInfo

object InputView {

    fun inputPlayerInfos() = inputPlayerNames().map {
        PlayerInfo(it, inputBettingMoney(it))
    }

    private fun inputPlayerNames(): List<String> {
        return try {
            println("게임에 참여할 사람의 이름을 입력하세요")
            readLine()!!.split(",").map { it.trim() }
        } catch (e: Exception) {
            println("ERROR : " + e.message)
            inputPlayerNames()
        }
    }

    private fun inputBettingMoney(name: String): Money {
        println("${name}의 베팅 금액을 입력하세요.")
        return Money(inputInteger())
    }

    private tailrec fun inputInteger(): Int {
        return readLine()?.toInt() ?: inputInteger()
    }

    tailrec fun askDrawMore(name: String): YesOrNo {
        println("${name}은 한 장의 카드를 더 받으시겠습니까 (예는 y, 아니오는 n)")
        return readLine()?.toYesOrNo() ?: askDrawMore(name)
    }
}
