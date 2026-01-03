package uv.tc.packetworldclientemovil.poko

data class Envio(
    var idEnvio: Int,
    val noGuia: String,
    var idEstatusEnvio: Int,
    var estatus: String?,
    var motivo: String,
    var idColaborador: Int?,

    val nombreSucursal: String,
    val CUSSucursal: String,
    val estadoSucursal: String,
    val ciudadSucursal: String,
    val coloniaSucursal: String,
    val codigoPostalSucursal: Int,
    val calleSucursal: String,
    val numeroSucursal: Int,

    val nombreDest: String,
    val apellidoPatDest: String,
    val apellidoMatDest: String,
    val estadoDest: String,
    val ciudadDest: String,
    val coloniaDest: String,
    val codigoPostalDest: Int,
    val calleDest: String,
    val numDest: Int,

    val paquetes: List<Paquete>,

    val nombreCliente: String,
    val apellidoPatCliente: String,
    val apellidoMatCliente: String,
    val correoCliente: String,
    val telefonoCliente: String
)
