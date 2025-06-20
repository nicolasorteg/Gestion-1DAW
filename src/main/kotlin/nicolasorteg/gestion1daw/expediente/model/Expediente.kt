package nicolasorteg.gestion1daw.expediente.model

data class Expediente(
    val calificaciones: Map<String, Double>,
    val notaMedia: Double,
    val observaciones: String
)
