package domain

class Player(name: String = "", hand: Hand = Hand()) : Participant(name, hand) {
    //    override fun draw(card: Card) {
//        hand.addCard(card)
//    }
//
//    fun score(): Int {
//        return hand.getScore()
//    }
//
//    fun isBust(): Boolean {
//        return hand.isBust()
//    }
    override fun isHitStatus(): Boolean {
        return !hand.isBust()
    }

}
