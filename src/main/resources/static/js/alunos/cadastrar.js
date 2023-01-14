
document.addEventListener('DOMContentLoaded', () => {

    // IDs
    const idPessoa = document.querySelector('#id-pessoa')
    const idAluno = document.querySelector('#id-aluno')

    // FORMS
    const formPessoa = document.querySelector('#form-pessoa')
    const formAluno = document.querySelector('#form-aluno')

    // BUTTON ACTIONS
    const btnSalvar = document.querySelector('#btn-salvar')
    btnSalvar.addEventListener('click', async function() {
        if(formIsValid()){
            loaderStart()
            await salvarInformacoes()
            loaderStop()
        }
    });

    // VALID FORMS
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

    // PERSIST INFOS
    async function salvarInformacoes(){
        await salvarPessoa().then(response => idPessoa.value = response)
        await salvarAluno().then(response => idAluno.value = response)
    }

    function salvarPessoa(){
        const pessoa = formPessoaToJson()
        const init = buildResquestInit(pessoa)

        return (fetch('/pessoas', init)
            .then(response => response.json())
            .catch(error => console.error(error))
        )
    }

    function salvarAluno(){
        const aluno = formAlunoToJson()   
        const init = buildResquestInit(aluno)

        return (fetch('/alunos', init)
            .then(response => response.json())
            .catch(error => console.error(error))
        )
    }

    // FORM TO JSON
    function formPessoaToJson(){
        const formData = new FormData(formPessoa)
        return Object.fromEntries(formData)
    }
    
    function formAlunoToJson(){
        const formData = new FormData(formAluno)
        const aluno = Object.fromEntries(formData)
        aluno['pessoa'] = idPessoa.value
        return aluno
    }

    // BUILD INIT FOR POST
    function buildResquestInit(object){
        return {
            method: 'POST', 
            body: JSON.stringify(object),
            headers: {
                'Content-type': 'application/json; charset=UTF-8'
            }
        }
    }
})
