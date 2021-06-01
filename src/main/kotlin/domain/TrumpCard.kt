package domain

class TrumpCard(val trumpCardNumber: TrumpCardNumber, val trumpCardPattern: TrumpCardPattern) {


    fun getScore(): Int {
        return trumpCardNumber.score
    }
}