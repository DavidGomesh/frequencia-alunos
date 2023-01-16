
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
    const formPessoa = document.querySelector('#form-pessoa')
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
        const formPessoaValid = formPessoaIsValid()
        const formAlunoValid = formAlunoIsValid()
        return formPessoaValid && formAlunoValid
    }

    function formPessoaIsValid(){
        formPessoa.classList.add('was-validated')
        return !formPessoa.querySelector(':invalid')
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
        aluno['pessoa'] = formPessoaToJson()
        return aluno
    }

    function formPessoaToJson(){
        const formData = new FormData(formPessoa)
        return Object.fromEntries(formData)
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
