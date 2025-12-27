// Historial estatus envio
function buscarHistorial() {
    const noGuia = document.getElementById("noGuia")?.textContent;

    if (!noGuia) {
        alert("No. de guía inválido");
        return;
    }

    consultarHistorial(noGuia)

    
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
            window.location.href = "HistorialEstatus.html";

        } catch (error) {
            console.error("Error al consultar envío:", error);
            alert("No se pudo obtener la historial del envío");
        }
}

document.addEventListener("DOMContentLoaded", () => {

    const historialJSON = localStorage.getItem("historialEnvio");

    if (!historialJSON) {
        console.warn("No hay historial del envio");
        return;
    }

    const historial = JSON.parse(historialJSON)

    mostrarHistorial(historial);
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