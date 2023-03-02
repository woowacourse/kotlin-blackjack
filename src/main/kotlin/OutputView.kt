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
        if (isReceived) {
            println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
        } else {
            println("딜러는 17이상이라 한장의 카드를 더 받지 않았습니다.")
        }
    }
}
