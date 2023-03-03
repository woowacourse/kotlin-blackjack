package blackjack.dto

class HandsDTO(
    val dealerName: String,
    val dealerHand: List<String>,
    val playerHands: Map<String, List<String>>
)
