//Consultar detalles de envio
function buscarEnvio() {
    const noGuia = document.getElementById("inputNoGuia")?.value;

    if (!noGuia) {
        alert("No. de guía inválido");
        return;
    }

    // Guardar el número de guía para futuras recargas
    localStorage.setItem("ultimaGuiaBuscada", noGuia);
    consultarDetallesEnvio(noGuia);
}

async function consultarDetallesEnvio(noGuia) {

    const URL_WS_Detalles_Envio =
        `http://localhost:8084/APIPacketWorld/api/envio/buscar-envio-web/${noGuia}`;

    try {
        const respuesta = await fetch(URL_WS_Detalles_Envio, {
            method: "GET"
        });

        if (!respuesta.ok) {
            throw new Error(`Error ${respuesta.status}`);
        }

        const data = await respuesta.json();
        console.log("Respuesta WS:", data);

        if (data.error) {
            alert(data.mensaje);
            return;
        }

        localStorage.setItem("envioSeleccionado", JSON.stringify(data.envio));
        
        // Si ya estamos en DetallesEnvio.html, solo actualizar la vista
        if (window.location.pathname.includes("DetallesEnvio.html")) {
            mostrarDetallesEnvio(data.envio);
        } else {
            window.location.href = "DetallesEnvio.html";
        }

    } catch (error) {
        console.error("Error al consultar envío:", error);
        alert("No se pudo obtener la información del envío");
    }
}

document.addEventListener("DOMContentLoaded", () => {

    // Solo ejecutar en DetallesEnvio.html
    if (!window.location.pathname.includes("DetallesEnvio.html")) {
        return;
    }

    // Primero intentar obtener el número de guía guardado
    const ultimaGuia = localStorage.getItem("ultimaGuiaBuscada");
    
    if (!ultimaGuia) {
        console.warn("No hay número de guía guardado.");
        alert("No se encontró información del envío. Redirigiendo al inicio...");
        window.location.href = "index.html";
        return;
    }

    // Consultar datos actualizados del servidor
    consultarDetallesEnvio(ultimaGuia);
});

function mostrarDetallesEnvio(envio) {

    //envio
    document.getElementById("noGuia").textContent = envio.noGuia;
    document.getElementById("estatus").textContent = envio.estatus;
    document.getElementById("fecha").textContent = envio.tiempo;
    document.getElementById("motivo").textContent = envio.motivo;
    document.getElementById("nombreConductor").textContent =
        envio.nombreConductor
            ? `${envio.nombreConductor} ${envio.apellidoPatConductor} ${envio.apellidoMatConductor}`
            : "No asignado";
    if (envio.paquetes==null){
        document.getElementById("paquetes").textContent=0;
    }else{
        document.getElementById("paquetes").textContent = envio.paquetes.length;
    }
    
    //cliente
    document.getElementById("nombreCliente").textContent =
        `${envio.nombreCliente} ${envio.apellidoPatCliente} ${envio.apellidoMatCliente}`;

    document.getElementById("telefCliente").textContent = envio.telefonoCliente;
    document.getElementById("correoCliente").textContent = envio.correoCliente;
    document.getElementById("colCliente").textContent = envio.coloniaCliente;
    document.getElementById("cpCliente").textContent = envio.codigoPostalCliente;
    document.getElementById("calleCliente").textContent = envio.calleCliente;

    //destinario
    document.getElementById("nombreDest").textContent =
        `${envio.nombreDest} ${envio.apellidoPatDest} ${envio.apellidoMatDest}`;

    document.getElementById("cdDest").textContent = envio.ciudadDest;
    document.getElementById("colDest").textContent = envio.coloniaDest;
    document.getElementById("cpDest").textContent = envio.codigoPostalDest;
    document.getElementById("calleDest").textContent = envio.calleDest;
    document.getElementById("numeroDest").textContent = envio.numDest;

    //sucursal
    document.getElementById("nombreSucursal").textContent = envio.nombreSucursal;
    document.getElementById("estado").textContent = envio.estadoSucursal;
    document.getElementById("cdSuc").textContent = envio.ciudadSucursal;
    document.getElementById("colSuc").textContent = envio.coloniaSucursal;
    document.getElementById("cpSuc").textContent = envio.codigoPostalSucursal;
    document.getElementById("calleSuc").textContent = envio.calleSucursal;
    document.getElementById("numeroSuc").textContent = envio.numeroSucursal;

}

function mostrarSeccion(id, boton) {

    // Ocultar todas las secciones
    document.querySelectorAll(".seccion").forEach(sec => {
        sec.classList.add("oculto");
    });

    // Mostrar la sección seleccionada
    document.getElementById(id).classList.remove("oculto");

    // Quitar clase activa
    document.querySelectorAll(".tab").forEach(btn => {
        btn.classList.remove("act");
    });

    // Activar botón
    boton.classList.add("act");
}
