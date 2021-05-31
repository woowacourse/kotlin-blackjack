package view

import domain.player.Money
import domain.player.Player

object InputView {

    fun inputPlayers(names : List<String>): List<Player> {
        return names.map {
            Player(it, inputBettingMoney(it))
        }
    }

    fun inputPlayerNames(): List<String> {
        return try {
            println("게임에 참여할 사람의 이름을 입력하세요")
            readLine()!!.split(",").map { it.trim() }
        } catch (e: Exception) {
            println("ERROR : " + e.message)
            inputPlayerNames()
        }
    }

    private fun inputBettingMoney(name: String): Money {
        return try {
            println("${name}의 베팅 금액은?")
            Money(readLine()!!.toInt())
        } catch (e: Exception) {
            println("ERROR : " + e.message)
            inputBettingMoney(name)
        }
    }

    fun askDrawMore(name: String): YesOrNo {
        return try {
            println("${name}은 한 장의 카드를 더 받으시겠습니까 (예는 y, 아니오는 n)")
            YesOrNo.parse(readLine()!!)
        } catch (e: Exception) {
            println("ERROR : " + e.message)
            askDrawMore(name)
        }
    }
}