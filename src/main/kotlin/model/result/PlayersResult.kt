package model.result

import model.participants.ParticipantName

data class PlayersResult(val result: Map<ParticipantName, ResultType>)
