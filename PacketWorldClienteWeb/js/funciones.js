function buscarEnvio(){
    const noGuia= document.getElementById("inputNoGuia").value;
    consultarDetallesEnvio(noGuia);
    //window.location.href = "DetallesEnvio.html";
}

async function consultarDetallesEnvio(noGuia) {

    if (!noGuia) {
        alert("No. de guía inválido");
        return;
    }

    const URL_WS_Detalles_Envio =
        `http://localhost:8084/APIPacketWorld/api/envio/buscar-envio-web/${noGuia}`;

    try {
        const respuesta = await fetch(URL_WS_Detalles_Envio, {
            method: 'GET'
        });

        if (!respuesta.ok) {
            throw new Error(`Error ${respuesta.status}`);
        }

        const envio = await respuesta.json();
        console.log(envio);

        mostrarDetallesEnvio(envio);

    } catch (error) {
        console.error("Error al consultar envío:", error);
        alert("No se pudo obtener la información del envío");
    }
}


function mostrarDetallesEnvio(envio) {

    /* ===== ENVÍO ===== */
    document.getElementById("noGuia").textContent = envio.noGuia;
    document.getElementById("estatus").textContent = envio.estatus;
    document.getElementById("fecha").textContent = envio.fechaUltimoCambio;
    document.getElementById("motivo").textContent = envio.motivo;

    document.getElementById("nombreConductor").textContent =
        envio.conductor?.nombreCompleto || "No asignado";

    /* ===== REMITENTE ===== */
    document.getElementById("nombreCliente").textContent =
        envio.remitente.nombre;

    document.getElementById("telefCliente").textContent =
        envio.remitente.telefono;

    document.getElementById("correoCliente").textContent =
        envio.remitente.correo;

    document.getElementById("colCliente").textContent =
        envio.remitente.direccion.colonia;

    document.getElementById("cpCliente").textContent =
        envio.remitente.direccion.codigoPostal;

    document.getElementById("calleCliente").textContent =
        envio.remitente.direccion.calle;

    /* ===== DESTINATARIO ===== */
    document.getElementById("nombreDest").textContent =
        envio.destinatario.nombre;

    document.getElementById("cdDest").textContent =
        envio.destinatario.ciudad;

    document.getElementById("colDest").textContent =
        envio.destinatario.colonia;

    document.getElementById("cpDest").textContent =
        envio.destinatario.codigoPostal;

    document.getElementById("calleDest").textContent =
        envio.destinatario.calle;

    document.getElementById("numeroDest").textContent =
        envio.destinatario.numero;

    /* ===== SUCURSAL ===== */
    document.getElementById("estado").textContent =
        envio.sucursal.estado;

    document.getElementById("cdSuc").textContent =
        envio.sucursal.ciudad;

    document.getElementById("colSuc").textContent =
        envio.sucursal.colonia;

    document.getElementById("cpSuc").textContent =
        envio.sucursal.codigoPostal;

    document.getElementById("calleSuc").textContent =
        envio.sucursal.calle;

    document.getElementById("numeroSuc").textContent =
        envio.sucursal.numeroExterior;
}



function mostrarSeccion(id, boton) {

    // Ocultar todas las secciones
    document.querySelectorAll(".seccion").forEach(sec => {
        sec.classList.add("oculto");
    });

    // Mostrar la sección seleccionada
    document.getElementById(id).classList.remove("oculto");

    // Quitar clase activa de todos los botones
    document.querySelectorAll(".tab").forEach(btn => {
        btn.classList.remove("act");
    });

    // Activar solo el botón clicado
    boton.classList.add("act");
}
