package model

class CardDistributer(private val cardFactory: CardFactory) {
    fun distribute(user: User, count: Int) {
        repeat(count) { user.cards.value.add(cardFactory.generate()) }
    }
}
