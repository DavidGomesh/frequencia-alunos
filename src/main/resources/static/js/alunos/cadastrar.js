
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
    const formCartao = document.querySelector('#form-cartao')
    const formEstPes = document.querySelector('#form-estagio-pesquisa')

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
        const formsValid = [
            formPessoaIsValid(), formAlunoIsValid(),
            formCartaoIsValid(), formPesEstIsValid(),
        ]
        return !formsValid.includes(false)
    }

    function formPessoaIsValid(){
        formPessoa.classList.add('was-validated')
        return !formPessoa.querySelector(':invalid')
    }

    function formAlunoIsValid(){
        formAluno.classList.add('was-validated')
        return !formAluno.querySelector(':invalid')
    }
    
    function formCartaoIsValid(){
        formCartao.classList.add('was-validated')
        return !formCartao.querySelector(':invalid')
    }

    function formPesEstIsValid(){
        formEstPes.classList.add('was-validated')
        return !formEstPes.querySelector(':invalid')
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
        aluno['cartao'] = formCartaoToJson()['codigo']
        return aluno
    }

    function formPessoaToJson(){
        const formData = new FormData(formPessoa)
        return Object.fromEntries(formData)
    }
    
    function formCartaoToJson(){
        const formData = new FormData(formCartao)
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
