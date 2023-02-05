
/**
 * Cadastrar alunos
 * @author David Gomesh
 */
document.addEventListener('DOMContentLoaded', () => {

    // Não deixa os forms serem submetidos
    document.querySelectorAll('form').forEach(form => {
        form.addEventListener('submit', event => {
            event.preventDefault()
        })
    })

    // Forms
    const formAluno = document.querySelector('#form-aluno')

    // Button Actions
    const btnSalvar = document.querySelector('#btn-salvar')
    btnSalvar.addEventListener('click', () => {
        salvar()
    });

    // Salvar
    function salvar(){
        if(formIsValid()){
            loaderStart()
            salvarAluno()
            .finally(() => {
                loaderStop()
                showSuccessAlert()
            })
        }
    }

    // Valid Forms
    function formIsValid(){
        const formsValid = [formAlunoIsValid()]
        return !formsValid.includes(false)
    }

    function formAlunoIsValid(){
        formAluno.classList.add('was-validated')
        return !formAluno.querySelector(':invalid')
    }
    
    // Persist Infos
    function salvarAluno(){
        const aluno = formAlunoToJson()
        const init = buildResquestInit(aluno)

        return (fetch('/alunos', init)
            .then(response => response.json())
            .catch(error => console.error(error))
        )
    }

    // Form To Json
    function formAlunoToJson(){
        const formData = new FormData(formAluno)
        const aluno = Object.fromEntries(formData)
        return aluno
    }

    // Build Init For Post
    function buildResquestInit(object){
        return {
            method: 'POST',
            body: JSON.stringify(object),
            headers: {
                'Content-type': 'application/json; charset=UTF-8'
            }
        }
    }

    // Alerts
    function showSuccessAlert(){
        Swal.fire({
            icon: 'success',
            title: 'Aluno cadastrado com sucesso!',
        }).then((result) => {
            if(result.isConfirmed){
                location.replace('/')
            }
        })
    }
})
