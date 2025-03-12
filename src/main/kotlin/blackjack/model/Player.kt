package blackjack.model

class Player(
    val name: String,
    override val hand: Hand,
    val betAmount: Amount
    ) : Participant{
        override val money = betAmount.toMinus()
    }
