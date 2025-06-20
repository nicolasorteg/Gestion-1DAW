package nicolasorteg.gestion1daw.expediente.dto

data class ExpedienteDto(
    val calificaciones: Map<String, Double>,
    val notaMedia: Double,
    val observaciones: String
)
