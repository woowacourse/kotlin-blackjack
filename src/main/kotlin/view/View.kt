package view

import domain.user.Gamers
import domain.user.User

class View {

    fun guideGameStart(): String {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readLine() ?: ""
    }

    fun guideDealCard(gamers: Gamers, numDealCard: Int) {
        println("${gamers.gamers.map { it.name }.joinToString()}에게 ${numDealCard}장의 나누었습니다.")
    }

    fun printPlayerCard(user: User) {
        print(
            "${user.name}: ${
                user.hand.cards.map { it -> it.trumpCardNumber.uniqueName + it.trumpCardPattern.koreaName }
                    .joinToString()
            } "
        )
        println("- 결과: ${user.score()}")
    }

    fun printPlayersCard(gamers: Gamers) {
        for (gamer: User in gamers.gamers) {
            printPlayerCard(gamer)
        }
    }
}