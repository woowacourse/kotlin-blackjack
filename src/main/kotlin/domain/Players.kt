package domain

class Players(
    val dealer: Dealer,
    val users: List<User>
) {
    val dealerScore get() = dealer.cards.score
}
