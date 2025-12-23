package uv.tc.packetworldclientemovil.poko

data class Conductor(
    var idColaborador: Int,
    var noPersonal: String,
    var nombre: String,
    var apellidoPaterno: String,
    var apellidoMaterno: String?,
    var CURP: String,
    var correo: String,
    var contraseña: String,
    var estatus: Int,
    var idRol: Int,
    var rol: String,
    var idSucursal: Int,
    var CUS: String,
    var idConductor: Int,
    var noLicencia: String,
    var fotoBase64: String?
)
