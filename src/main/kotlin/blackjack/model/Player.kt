package blackjack.model

class Player(val name: String, deck: Deck) : GameParticipant(HandCards(deck)) {
    fun addCard(playerInput: Boolean): Boolean =
        if (isCanAddCard(playerInput)) {
            handCards.add()
            true
        } else false

    private fun isCanAddCard(isAdd: Boolean) = isAdd && !isBust()
}
