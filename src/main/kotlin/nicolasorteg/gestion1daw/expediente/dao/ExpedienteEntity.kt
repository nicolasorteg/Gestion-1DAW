package nicolasorteg.gestion1daw.expediente.dao

data class ExpedienteEntity(
    val calificaciones: Map<String, Double>,
    val notaMedia: Double,
    val observaciones: String
)