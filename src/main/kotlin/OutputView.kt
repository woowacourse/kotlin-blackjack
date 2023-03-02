object OutputView {

    fun printCardDividingMessage(dealer: Dealer, players: List<Player>) {
        println("딜러와${players.joinToString(",") { it.name }}에게 2장의 카드를 나누었습니다.")
        println("딜러: ${dealer.cards.cards.first()}")
        players.forEach { player -> printCardResults(player) }
    }

    fun printCardResults(player: Player) {
        println("${player.name}카드: ${player.cards.cards.joinToString(", ")}")
    }

    fun printIsDealerReceivedCard(isReceived: Boolean) {
        // TODO: 함수 분리
        if (isReceived) {
            println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
        } else {
            println("딜러는 17이상이라 한장의 카드를 더 받지 않았습니다.")
        }
    }

    fun printFinalCards(dealer: Dealer, players: List<Player>) {
        println("딜러 카드: ${dealer.cards.cards.joinToString(", ")} - 결과: ${dealer.cards.getSum()}")
        // TODO: 함수 분리
        players.forEach { player ->
            println("${player.name}카드: ${player.cards.cards.joinToString(", ")} - 결과: ${player.cards.getSum()}")
        }
    }

    fun printGameResults(playerGameResults: List<PlayerGameResult>, dealerGameResult: List<GameResult>) {
        println("###최종 승패")
        // TODO: 함수 분리
        println(
            "딜러: %d승 %d패 %d무".format(
                dealerGameResult.count { gameResult -> gameResult == GameResult.WIN },
                dealerGameResult.count { gameResult -> gameResult == GameResult.LOSE },
                dealerGameResult.count { gameResult -> gameResult == GameResult.DRAW }
            )
        )
        playerGameResults.forEach { playerGameResult ->
            println("%s: %s".format(playerGameResult.playerName, playerGameResult.gameResult.description))
        }
    }
}
