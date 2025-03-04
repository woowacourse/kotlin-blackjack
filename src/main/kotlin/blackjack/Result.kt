package blackjack

class Result() {
    fun getWinner(dealer: Dealer, player: Player): Participant? {
        return if (dealer.totalSum > player.totalSum) {
            dealer
        } else if (player.totalSum > dealer.totalSum) {
            player
        } else {
            null
        }
    }

}