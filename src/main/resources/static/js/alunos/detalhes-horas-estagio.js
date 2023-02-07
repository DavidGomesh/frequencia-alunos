
document.addEventListener('DOMContentLoaded', () => {

    // Não deixa os forms serem submetidos
    document.querySelectorAll('form').forEach(form => {
        form.addEventListener('submit', event => {
            event.preventDefault()
        })
    })

    const modal = new bootstrap.Modal('#modalEditarHoraEstagio', {})
    const formHoras = document.querySelector('#form-horas')

    document.querySelector('#modalEditarHoraEstagio').addEventListener('hidden.bs.modal', () => {
        formHoras.classList.remove('was-validated')
        document.querySelector('#horaEntrada').value = null
        document.querySelector('#horaSaida').value = null
    })
    
    const btnEditarHorasEstagio = document.querySelectorAll('.btnEditarHorasEstagio')
    btnEditarHorasEstagio.forEach(btn => {
        btn.addEventListener('click', function(){
            preparModal(btn)
            modal.show()
        })
    })

    const btnSalvar = document.querySelector('#btnSalvarEdicaoHorasEstagio')
    btnSalvar.addEventListener('click', () => {
        salvarHoras().then(() => location.reload())
    })

    function preparModal(btn){

        document.querySelector('#idHorasEstagio').value = btn.dataset['idHorasEstagio']
        const horaEntrada = {
            horas: btn.dataset['horaInicioHoras'],
            minutos: btn.dataset['horaInicioMinutos'],
            segundos: btn.dataset['horaInicioSegundos']
        }

        const horaSaida = {
            horas: btn.dataset['horaFimHoras'],
            minutos: btn.dataset['horaFimMinutos'],
            segundos: btn.dataset['horaFimSegundos']
        }

        const inputHoraEntrada = document.querySelector('#horaEntrada')
        const inputHoraSaida = document.querySelector('#horaSaida')

        inputHoraEntrada.value = horaToString(horaEntrada)

        if(horaSaida.horas == 0 && horaSaida.minutos == 0 && horaSaida.segundos == 0){
            return
        }

        inputHoraSaida.value = horaToString(horaSaida)
    }

    function horaToString(hora){
        const horas = doisDigitos(Number(hora.horas))
        const minutos = doisDigitos(Number(hora.minutos))
        const segundos = doisDigitos(Number(hora.segundos))
        return `${horas}:${minutos}:${segundos}`;
    }

    function doisDigitos(numero){
        return numero.toLocaleString('en-US', {minimumIntegerDigits: 2, useGrouping:false});
    }

    // Salvar horas
    function salvarHoras(){
        const idHorasEstagio = document.querySelector('#idHorasEstagio').value
        if(validarForm()){
            const horas = formHorasToJson()
            const init = buildResquestInit(horas)
            return (fetch('/horas-estagio/' + idHorasEstagio, init)
                .then(response => response.json())
                .catch(error => console.error(error))
            )
        }

        return Promise.reject()
    }

    // Validar
    function validarForm(){
        formHoras.classList.add('was-validated')
        return !formHoras.querySelector(':invalid')
    }

    // Form Horas to JSON
    function formHorasToJson(){
        const formData = new FormData(formHoras)
        const horas = Object.fromEntries(formData)
        return horas
    }

    // Build Init For Post
    function buildResquestInit(object){
        return {
            method: 'PUT',
            body: JSON.stringify(object),
            headers: {
                'Content-type': 'application/json; charset=UTF-8'
            }
        }
    }

})