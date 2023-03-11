package domain.result

import domain.participants.Dealer

class ParticipantsResult(val dealer: Dealer, val playerResult: List<PlayerResult>)
