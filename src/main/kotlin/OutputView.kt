object OutputView {

    fun printCardDividingMessage(dealer: Dealer, players: List<Player>) {
        println("딜러와${players.joinToString(",") { it.name }}에게 2장의 카드를 나누었습니다.")
        println("딜러: ${dealer.cards.cards.first()}")
        players.forEach { player -> "${player.name}카드: ${player.cards.cards.joinToString(", ")}" }
    }
}
