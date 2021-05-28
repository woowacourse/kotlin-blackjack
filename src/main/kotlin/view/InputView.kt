package view

import domain.player.Player

object InputView {

    fun inputPlayerNames(): List<String> {
        return try {
            println("게임에 참여할 사람의 이름을 입력하세요")
            readLine()!!.split(",").map { it.trim() }
        } catch (e: Exception) {
            println("ERROR : " + e.message)
            inputPlayerNames()
        }
    }

    fun inputBettingMoney(name: String): Int {
        return try {
            println("$name 의 베팅 금액은?")
            readLine()!!.toInt()
        } catch (e: Exception) {
            println("ERROR : " + e.message)
            inputBettingMoney(name)
        }
    }

    fun askDrawMore(player: Player): YesOrNo {
        return try {
            println("${player.name}은 한 장의 카드를 더 받으시겠습니까 (예는 y, 아니오는 n)")
            YesOrNo.parse(readLine()!!)
        } catch (e: Exception) {
            println("ERROR : " + e.message)
            askDrawMore(player)
        }
    }
}