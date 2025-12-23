package uv.tc.packetworldclientemovil.dto

import uv.tc.packetworldclientemovil.poko.Conductor

data class RSAutenticacionConductor(
    val error : Boolean,
    val mensaje : String,
    var conductor: Conductor?
)
