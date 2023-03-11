package domain.result

import domain.participants.Dealer

data class ParticipantsResult(val dealer: Dealer, val playerResult: List<PlayerResult>)
