package view

import domain.Winning
import domain.user.Dealer
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

    fun guideContinueMessage(gamer: User) {
        println("${gamer.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        readLine()
    }

    fun printDealerDraw() {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun printMatchResult(winnerLog: Winning, dealer: Dealer, gamers: Gamers) {
        println("## 최종 승패")
        println("${dealer.name}: ${winnerLog.dealerWin}승 ${winnerLog.dealerLose}패")
        for (gamer: User in gamers.gamers) {
            println("${gamer.name}: ${winnerLog.winningLog[gamer.name]}")
        }
    }
}