package blackjack.domain

class Dealer(name: String = DEALER_NAME): Participant(name) {
    companion object {
        private const val DEALER_NAME: String = "딜러"
    }
}