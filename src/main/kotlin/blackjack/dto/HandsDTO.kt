package blackjack.dto

data class HandsDTO(
    val dealerHand: HandDTO,
    val playerHands: List<HandDTO>,
)
