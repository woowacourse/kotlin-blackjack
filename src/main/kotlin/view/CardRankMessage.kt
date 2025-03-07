package view

import model.CardRank

object CardRankMessage {
    private val messages =
        mapOf(
            CardRank.ACE to "A",
            CardRank.KING to "K",
            CardRank.QUEEN to "Q",
            CardRank.JACK to "J",
            CardRank.TWO to "2",
            CardRank.THREE to "3",
            CardRank.FOUR to "4",
            CardRank.FIVE to "5",
            CardRank.SIX to "6",
            CardRank.SEVEN to "7",
            CardRank.EIGHT to "8",
            CardRank.NINE to "9",
            CardRank.TEN to "10",
        )

    fun getCardRankMessage(cardRank: CardRank): String {
        return messages[cardRank] ?: ""
    }
}
