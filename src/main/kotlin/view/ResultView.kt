package view

import domain.*

class ResultView {
    fun printGameInit(players: Players) {
        println("\n딜러와 ${formatStringName(players)}에게 2장의 나누었습니다.")
    }

    fun printInitCards(participants: Participants) {
        participants.participants.forEach { participant ->
            println(formatStringInitCards(participant))
        }
        println()
    }

    fun printPlayerCard(player: Player) {
        println("${player.name.name}카드: ${formatStringCards(player)}")
    }

    fun printDealerAddCard() {
        println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun printGameResult(players: Players, dealer: Dealer) {
        println("\n## 최종승패")
        formatStringGameResult(players, dealer)
    }

    private fun formatStringName(players: Players): String {
        val sb = StringBuilder()
        players.players.forEach { sb.append(it.name.name + ", ") }
        sb.delete(sb.length - 2, sb.length)
        return sb.toString()
    }

    private fun formatStringCards(participant: Participant): String {
        val sb = StringBuilder()
        participant.cards.cards.forEach { sb.append("${it.cardNumber.number}${it.cardCategory.pattern}" + ", ") }
        sb.delete(sb.length - 2, sb.length)
        return sb.toString()
    }

    private fun formatStringInitCards(participant: Participant): String {
        val sb = StringBuilder()
        sb.append(participant.name.name + ": ")
        participant.showInitCards().forEach { sb.append("${it.cardNumber.number}${it.cardCategory.pattern}" + ", ") }
        sb.delete(sb.length - 2, sb.length)
        return sb.toString()
    }

    private fun formatStringGameResult(players: Players, dealer: Dealer) {
        val dealerResult = dealer.getResult(players)
        println("딜러: ${dealerResult[GameResult.WIN]}승 ${dealerResult[GameResult.LOSE]}패")
        players.players.forEach { player ->
            val playerResult = player.getGameResult(dealer.getSumStateResult())
            if (playerResult == GameResult.WIN) {
                println("${player.name.name}: 승")
            } else {
                println("${player.name.name}: 패")
            }
        }
    }

    fun printScore(participants: Participants) {
        participants.participants.forEach {
            println("${it.name.name} 카드: ${formatStringCards(it)} - 결과: ${it.getSumStateResult().sum}")
        }
    }
}

