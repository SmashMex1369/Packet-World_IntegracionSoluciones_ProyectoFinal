// Historial estatus envio
function buscarHistorial() {
    const noGuia = document.getElementById("noGuia")?.textContent;

    if (!noGuia) {
        alert("No. de guía inválido");
        return;
    }

    // Guardar el número de guía para futuras recargas
    localStorage.setItem("ultimaGuiaHistorial", noGuia);
    consultarHistorial(noGuia);
}

async function consultarHistorial(noGuia){

    const URL_WS_HistorialEnvio =
        `http://localhost:8084/APIPacketWorld/api/envio/historial-estatus-envio/${noGuia}`;

        try {
            const respuesta = await fetch(URL_WS_HistorialEnvio, {
                method: "GET"
            });

            if (!respuesta.ok) {
                throw new Error(`Error ${respuesta.status}`);
            }

            const historial= await respuesta.json();
            console.log(historial);

            if(historial.error){
                alert(historial.mensaje);
                return;
            }

            localStorage.setItem("historialEnvio", JSON.stringify(historial));
            
            // Si ya estamos en HistorialEstatus.html, solo actualizar la vista
            if (window.location.pathname.includes("HistorialEstatus.html")) {
                mostrarHistorial(historial);
            } else {
                window.location.href = "HistorialEstatus.html";
            }

        } catch (error) {
            console.error("Error al consultar envío:", error);
            alert("No se pudo obtener la historial del envío");
        }
}

document.addEventListener("DOMContentLoaded", () => {

    // Solo ejecutar en HistorialEstatus.html
    if (!window.location.pathname.includes("HistorialEstatus.html")) {
        return;
    }

    // Intentar obtener el número de guía guardado
    const ultimaGuia = localStorage.getItem("ultimaGuiaHistorial");
    
    if (!ultimaGuia) {
        console.warn("No hay número de guía guardado para el historial.");
        alert("No se encontró información del historial. Redirigiendo al inicio...");
        window.location.href = "index.html";
        return;
    }

    // Consultar datos actualizados del servidor
    consultarHistorial(ultimaGuia);
});

function mostrarHistorial(historial){

    document.getElementById("lbNoGuia").textContent= historial[0].noGuia;

    const tbody = document.getElementById("tablaHistorial");
    tbody.innerHTML = ""; 

    historial.forEach(item => {

        const fila = document.createElement("tr");

        if(item.motivo==undefined){
            item.motivo = ""
        }

        fila.innerHTML = `
            <td>${item.estatus}</td>
            <td>${item.tiempo}</td>
            <td>${item.motivo}</td>
        `;

        tbody.appendChild(fila);
    });
}