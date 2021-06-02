package domain.card

class TrumpCard(val trumpCardNumber: TrumpCardNumber, val trumpCardPattern: TrumpCardPattern) {


    fun getScore(): Int {
        return trumpCardNumber.score
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TrumpCard) return false

        if (trumpCardNumber != other.trumpCardNumber) return false
        if (trumpCardPattern != other.trumpCardPattern) return false

        return true
    }

    override fun hashCode(): Int {
        var result = trumpCardNumber.hashCode()
        result = 31 * result + trumpCardPattern.hashCode()
        return result
    }


}