let fCalendario = null;

document.addEventListener('DOMContentLoaded',function(){
	obtenerUsuario();
	
	fCalendario = flatpickr("#rangoFechasContainer", {
	        inline: true,
	        mode: "range",
	        locale: "es",
	        dateFormat: "Y-m-d",
	        disableMobile: "true",
	        onChange: function(selectedDates,_, instance) {
	            if (selectedDates.length === 2) {
	                document.getElementById('fechaInicio').value = instance.formatDate(selectedDates[0], "Y-m-d");
	                document.getElementById('fechaFin').value = instance.formatDate(selectedDates[1], "Y-m-d");
	            } else {
	                document.getElementById('fechaInicio').value = "";
	                document.getElementById('fechaFin').value = "";
	            }
	        }
	    });
	
});

function obtenerUsuario(){
	fetch('api/datosUsuario')
	.then(function(respuesta){
		return respuesta.json();
	})
	.then(function (usuario){
		//console.log(usuario);
		document.getElementById("nombreUsuario").textContent = usuario.nombre;
	});
}

function obtenerDatos(){
	fetch('api/datosColaborador')
	.then(function(respuesta) {
		//console.log(respuesta.status);
		return respuesta.json();
	})
	.then(function(data) {
		if(data && data.colaborador){
			
			if (data.colaborador.foto) 
			    document.getElementById('fotoPerfil').src = hexABase64(data.colaborador.foto);
			else {
				document.getElementById('fotoPerfil').src =
				'https://ui-avatars.com/api/?name=' + encodeURIComponent(`${data.usuario.firstName} ${data.usuario.lastName}`) + '&background=1a365d&color=fff';
			}
			
			//personales/Fiscales
			document.getElementById("nombreCompleto").textContent = (data.usuario.firstName && data.usuario.lastName) ? `${data.usuario.firstName} ${data.usuario.lastName}` : "---";
			document.getElementById("usuario").textContent = data.usuario.ssoId || "---";
			document.getElementById("rfc").textContent = data.colaborador.rfc || "---";
			const estadosCivil = {"S": "Soltero", "C": "Casado", "D": "Divorciado", "V": "Viudo"};
			document.getElementById("estadoCivil").textContent = estadosCivil[data.colaborador.estadoCivil] || "---";
			document.getElementById("nss").textContent = data.colaborador.nss || "---";
			document.getElementById("fechaNac").textContent = data.colaborador.fechaNac || "---";
			document.getElementById("curp").textContent = data.colaborador.curp || "---";
			const generos = {"M": "Masculino", "F": "Femenino"};
			document.getElementById("genero").textContent = generos[data.colaborador.genero] || "---";
			
			//contacto y direccion
			document.getElementById("correoPersonal").textContent = data.colaborador.correoPersonal || "---";
			document.getElementById("telefonoPersonal").textContent = data.colaborador.telefonoPersonal || "---";
			document.getElementById("correoEmpresa").textContent = data.usuario.email || "---";
			document.getElementById("telefonoEmpresa").textContent = data.colaborador.telefonoEmpresa || "---";
			document.getElementById("direccionCompleta").textContent = data.colaborador.direccionCompleta || "---";
			
			//contacto de emergencia
			document.getElementById("nombreContacto").textContent = data.colaborador.contactoEmergenciaNombre || "---";
			document.getElementById("parentesco").textContent = data.colaborador.contactoEmergenciaParentesco || "---";
			document.getElementById("telefonoEmergenca").textContent = data.colaborador.contactoEmergenciaTelefono || "---";
			
			if(data.colaborador.contactoEmergenciaNombre2 || data.colaborador.contactoEmergenciaParentesco2 || data.colaborador.contactoEmergenciaTelefono2){
				document.getElementById("nombreContacto2").textContent = data.colaborador.contactoEmergenciaNombre2 || "---";
				document.getElementById("parentesco2").textContent = data.colaborador.contactoEmergenciaParentesco2 || "---";
				document.getElementById("telefonoEmergenca2").textContent = data.colaborador.contactoEmergenciaTelefono2 || "---";
				
				document.getElementById("contacto2").classList.remove("d-none");	
				
			}else{document.getElementById("contacto2").classList.add("d-none");}
			
			if(data.colaborador.contactoEmergenciaNombre3 || data.colaborador.contactoEmergenciaParentesco3 || data.colaborador.contactoEmergenciaTelefono3){
				document.getElementById("nombreContacto3").textContent = data.colaborador.contactoEmergenciaNombre3 || "---";
				document.getElementById("parentesco3").textContent = data.colaborador.contactoEmergenciaParentesco3 || "---";
				document.getElementById("telefonoEmergenca3").textContent = data.colaborador.contactoEmergenciaTelefono3 || "---";
							
				document.getElementById("contacto3").classList.remove("d-none");	
							
			}else{document.getElementById("contacto3").classList.add("d-none");}
			
			
			document.getElementById("informacionPerfil").classList.remove("d-none");
			document.getElementById("perfilNoEncontrado").classList.add("d-none");
		}else{
			document.getElementById("informacionPerfil").classList.add("d-none");
			document.getElementById("perfilNoEncontrado").classList.remove("d-none");
		}
	});
}

function hexABase64(hexString, mimeType = 'image/jpeg') {
    if (!hexString) return null;

    // Quitar el prefijo '\x' de Postgres si viene presente
    const hex = hexString.startsWith('\\x') ? hexString.substring(2) : hexString;

    // Convertir Hexadecimal a Binario
    let binary = '';
    for (let i = 0; i < hex.length; i += 2) {
        binary += String.fromCharCode(parseInt(hex.substr(i, 2), 16));
    }

    // Retornar la cadena Base64 lista para el src de <img>
    return `data:${mimeType};base64,${window.btoa(binary)}`;
}

function normalizarFecha(fecha) {
    if (!fecha) return '';
    const partes = fecha.toString().split(/[\s,/-]+/);
    if (partes.length < 3) return fecha;
    
    const anio = partes[0];
    const mes = partes[1].padStart(2, '0');
    const dia = partes[2].padStart(2, '0');
    
    return `${anio}-${mes}-${dia}`;
}

function periodosVac(){
	fetch('api/vacDisp')
	.then(function(periodos){
		return periodos.json();
	})
	.then(function(data){
		
		document.getElementById("diasDisponiblesTotales").textContent = data.diasDisponibles || 0;
		
		if(document.getElementById("modalDiasDisponibles"))
			document.getElementById("modalDiasDisponibles").textContent = data.diasDisponibles || 0;
		
		if(data.fechaHoy && fCalendario){
			const fHoy = normalizarFecha(data.fechaHoy);
			const partes = fHoy.split('-');
			
			const fechaServer = new Date(partes[0],partes[1]-1,partes[2]);
			fechaServer.setDate(fechaServer.getDate()+5);
			fCalendario.set('minDate', fechaServer);
		}
		
		if(document.getElementById("modUltimoDiaTrabajado"))
			document.getElementById("modUltimoDiaTrabajado").classList.toggle("d-none", data.esquema != 'ALTERNO');
		if(document.getElementById("ultimoDiaTrabajado"))
			document.getElementById("ultimoDiaTrabajado").required = data.esquema === 'ALTERNO';
		
		if(data.periodos && data.periodos.length > 0){
			
			document.getElementById("tablaPeriodos").innerHTML = "";
			
			data.periodos.forEach(function(p){
				
				const fechacad = normalizarFecha(p.fechaCaducidad);
				const fechaIni = normalizarFecha(p.fechaInicio);
				const fechaF = normalizarFecha(p.fechaFin);
				const fechaH = normalizarFecha(data.fechaHoy);
				
				const caduco = p.fechaCaducidad && (fechaH > fechacad);
				const badgeCaduco = caduco ? `<span class='badge text-white fw-bold' style='background-color: #c81430;'>${fechacad}</span>`
											: `<span class='badge bg-warning text-dark fw-bold'>${fechacad}</span>`;
				
				document.getElementById("tablaPeriodos").innerHTML += `
				<tr>
					<td class='fw-bold text-dark'>${p.anioPeriodo || '---'}</td>
					<td>
						<span class='fw-bold d-block text-dark'>${fechaIni || '---'}</span>
						<small class='text-muted'>Al</small>
						<span class='fw-bold d-block text-dark'>${fechaF|| '---'}</span>
					</td>
					<td>${badgeCaduco}</td>
					<td><span class='badge bg-success fs-6'>${p.diasOtorgados || 0}</span></td>
					<td><span class='badge fs-6' style='background-color: #c81430;'>${p.diasTomados || 0}</span></td>
					<td><span class='badge fs-6' style='background-color: #1a365d;'>${p.diasDisponibles || 0}</span></td>
				</tr>
				`;
			});
			
			document.getElementById("botonSolicitar").classList.toggle("d-none", data.diasDisponibles <= 0);
			document.getElementById("informacionPeriodos").classList.remove("d-none");
			document.getElementById("sinPeriodos").classList.add("d-none");
			
		}else{
			if(document.getElementById("botonSolicitar"))
				document.getElementById("botonSolicitar").classList.add("d-none");
			
			document.getElementById("informacionPeriodos").classList.add("d-none");
			document.getElementById("sinPeriodos").classList.remove("d-none");
		}
	});
}

function solicitudesVac(){
	fetch('api/solicitudesVac')
	.then(function(solicitudes){
		return solicitudes.json();
	})
	.then(function(data){
		if(data.solicitudes && data.solicitudes.length > 0){
			
			document.getElementById("tablaSolicitudes").innerHTML = "";
			
			data.solicitudes.forEach(function(s, index){
				const fInicio = normalizarFecha(s.fechaInicio);
				const fFin = normalizarFecha(s.fechaFin);
				const fCancelacion = normalizarFecha(s.fechaCancelacion);
				
				let badgeEstado = 'bg-warning text-dark';
				if (s.estado === 'APROBADA') badgeEstado = 'bg-success';
				if (s.estado === 'RECHAZADA') badgeEstado = 'bg-danger';
				if (s.estado === 'CANCELADA') badgeEstado = 'bg-secondary';
				
				const htmlJefe = generarHtmlValidacion(s.fechaRespuestaJefe, s.motivoRechazoJefe, 'JEFE');
				const htmlRH = generarHtmlValidacion(s.fechaRespuestaRh, s.motivoRechazoRh, 'RH');
				
				let htmlCancelacion = '';
				if (s.estado === 'CANCELADA') {
					htmlCancelacion =
					`<div class="card border border-secondary mb-3">
						<div class="card-header bg-secondary text-white fw-bold py-2">
							<i class="bi bi-x-circle me-2"></i>Detalle de Cancelación
						</div>
						<div class="card-body p-3 bg-light">
							<div class="mb-2">
								<small class="fw-bold text-muted d-block">Fecha de Cancelación:</small>
								<span class="text-dark fs-6 fw-semibold">${fCancelacion || '---'}</span>
							</div>
							<div>
								<small class="fw-bold text-muted d-block">Motivo de Cancelación:</small>
								<span class="text-dark fs-6">${s.motivoCancelacion || '---'}</span>
							</div>
						</div>
					</div>`;
				}
				
				document.getElementById("tablaSolicitudes").innerHTML += `
					<tr data-bs-toggle="modal" data-bs-target="#modalSolicitud-${index}">
						<td>
							<span class="fw-bold d-block text-dark">${fInicio || '---'}</span>
							<small class="text-muted">Al</small>
							<span class="fw-bold d-block text-dark">${fFin || '---'}</span>
						</td>
						<td><span class="badge bg-secondary fs-6">${s.diasSolicitados || 0}</span></td>
						<td class="text-start text-wrap" style="max-width: 200px;">
							<small class="text-dark">${s.motivo || '---'}</small>
						</td>
						<td>
							<div class="d-flex align-items-center justify-content-center gap-2">
								<span class="badge fs-6 ${badgeEstado}">${s.estado || 'PENDIENTE'}</span>
							</div>
						</td>
						<td>
							<i class="bi bi-chevron-right text-muted fs-6"></i>
						</td>
					</tr>`;
								
			document.getElementById("contenedorModales").innerHTML += `
				<div class="modal fade text-start" id="modalSolicitud-${index}" tabindex="-1" aria-hidden="true">
					<div class="modal-dialog modal-dialog-centered modal-lg">
						<div class="modal-content border-0 shadow">
							<div class="modal-header text-white" style="background-color: #1a365d;">
								<h5 class="modal-header-title modal-title fs-6 fw-bold">
									<i class="bi bi-info-circle me-2"></i>Detalle de Estado de la Solicitud
								</h5>
								<button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
							</div>
							<div class="modal-body p-4">
								<div class="p-3 bg-light rounded border mb-4 text-center">
									<small class="text-uppercase text-muted fw-bold d-block mb-1">Periodo Solicitado</small>
									<span class="fw-bold text-dark fs-6">${fInicio}</span>
									<span class="text-muted mx-2 fs-6 fw-normal">al</span>
									<span class="fw-bold text-dark fs-6">${fFin}</span>
									<span class="badge bg-primary ms-2">${s.diasSolicitados || 0} día(s)</span>
								</div>
															
								${htmlCancelacion}
															
								<div class="card mb-3 border">
									<div class="card-header bg-white fw-bold text-dark py-2">
										<i class="bi bi-person-badge me-2 text-primary"></i>Validación Jefe Directo
									</div>
										<div class="card-body p-3">${htmlJefe}</div>
									</div>
									<div class="card border">
									<div class="card-header bg-white fw-bold text-dark py-2">
										<i class="bi bi-building me-2 text-primary"></i>Validación Recursos Humanos
									</div>
										<div class="card-body p-3">${htmlRH}</div>
								</div>
							</div>
							<div class="modal-footer bg-light py-2">
								<button type="button" class="btn btn-secondary btn-sm px-3" data-bs-dismiss="modal">Cerrar</button>
							</div>
						</div>
					</div>
				</div>`;
			});

						
			
			document.getElementById("informacionSolicitudes").classList.remove("d-none");
			document.getElementById("sinSolicitudes").classList.add("d-none");
			
		}else{
			document.getElementById("informacionSolicitudes").classList.add("d-none");
			document.getElementById("sinSolicitudes").classList.remove("d-none");
		}
	});
	
}

function generarHtmlValidacion(fechaRespuesta, motivoRechazo, tipoRol) {
    if (!fechaRespuesta) {
        return `<span class="badge bg-light text-dark border px-3 py-2"><i class="bi bi-clock me-1"></i>Pendiente de revisión</span>`;
    }
    
    const fechaResp = normalizarFecha(fechaRespuesta);
    
    if (motivoRechazo && motivoRechazo !== '') {
        return `
            <small class="fw-bold d-block text-success mb-1"><i class="bi bi-check-circle me-1"></i>Atendido (${fechaResp})</small>
            <div class="badge bg-danger-subtle text-danger text-start d-block p-2 text-wrap">
                <span class="fw-bold d-block mb-1">Motivo rechazo:</span>
                <span class="fw-normal">${motivoRechazo}</span>
            </div>`;
    }
    
    const icono = tipoRol === 'JEFE' ? 'bi-person-check-fill' : 'bi-check-all';
    const textoAprobado = tipoRol === 'JEFE' ? 'Aprobado por Jefe' : 'Aprobado por RH';
    
    return `
        <small class="fw-bold d-block text-success mb-1"><i class="bi bi-check-circle me-1"></i>Atendido (${fechaResp})</small>
        <small class="badge bg-info-subtle text-primary border border-info-subtle px-2 py-1">
            <i class="bi ${icono} me-1"></i>${textoAprobado}
        </small>`;
}

function generarSolicitud(event){
	event.preventDefault();
	
	const fInci = document.getElementById("fechaInicio").value;
	const fFin = document.getElementById("fechaFin").value;
	
	if(!fInci || !fFin){
		mostrarModalRespuesta(false,"Debes seleccionar un rango de fechas valido.")
		return;
	}
	
	const params = new URLSearchParams();
	params.append("fechaInicio", fInci);
	params.append("fechaFin",fFin);
	params.append("motivo", document.getElementById("motivo").value);
	
	const inputUltimo = document.getElementById("ultimoDiaTrabajado");
	if (inputUltimo && inputUltimo.value)
		params.append("ultimoDiaTrabajado", inputUltimo.value);
	
	fetch('api/solicitar', {
		method: 'POST',
		headers: {'Content-Type': 'application/x-www-form-urlencoded'},
		body: params.toString()
	})
	.then(respuesta => respuesta.json()
	.then(data => ({ status: respuesta.status, body: data })))
	.then(res => {
		if(document.getElementById("modalNuevaSolicitud")){
			if(bootstrap.Modal.getInstance(document.getElementById("modalNuevaSolicitud")))
				bootstrap.Modal.getInstance(document.getElementById("modalNuevaSolicitud")).hide();
		}
		
		if (res.body.exito) {
			mostrarModalRespuesta(true, res.body.mensaje || "Solicitud enviada con éxito.");
			// Limpiar formulario y reiniciar selección de Flatpickr
			document.getElementById("formSolicitudVacaciones").reset();
			if(fCalendario)
				fCalendario.clear();
		}else{mostrarModalRespuesta(false, res.body.mensaje || "Ocurrio un error en la solicitud.");}
	});
}

function mostrarModalRespuesta(esExito, mensaje) {
    if (esExito) {
        document.getElementById("modalRespuestaHeader").className = "modal-header text-white bg-success";
        document.getElementById("modalRespuestaIcono").className = "bi bi-check-circle-fill me-2";
        document.getElementById("modalRespuestaTitulo").textContent = "¡Solicitud Exitosa!";
    } else {
        document.getElementById("modalRespuestaHeader").className = "modal-header text-white bg-danger";
        document.getElementById("modalRespuestaIcono").className = "bi bi-exclamation-triangle-fill me-2";
        document.getElementById("modalRespuestaTitulo").textContent = "Error en la Solicitud";
    }

    document.getElementById("modalRespuestaTexto").textContent = mensaje;

    const modalRespuesta = new bootstrap.Modal(document.getElementById('modalRespuesta'));
    modalRespuesta.show();
}
