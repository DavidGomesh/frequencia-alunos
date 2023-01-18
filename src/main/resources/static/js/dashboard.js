
/**
 * @author David Gomesh
 */
document.addEventListener('DOMContentLoaded', () => {

    preparartabelaEstagios()
    preparartabelaLogs()


    function preparartabelaEstagios(){

        const socket = new SockJS('/mywebsockets')
        const stompClient = Stomp.over(socket)
        stompClient.debug = null
        stompClient.connect({}, () => {
            stompClient.subscribe('/topic/contagem-horas', () => {
                atualizarTabelaEstagios()
            })
        })

        const tabelaEstagios = document.querySelector('#tabela-estagios>tbody')

        // Atualia a tabela
        atualizarTabelaEstagios()

        // Atualiza a tabela
        function atualizarTabelaEstagios(){
            fetch('/estagios/ativos')
            .then(response => response.json())
            .then(estagios => tabelaEstagios.innerHTML = construirTabela(estagios))
        }

        // Constroi a tabela
        function construirTabela(estagios = []){
            return estagios.reduce((prevTr, estagio) => {
                return prevTr += `<tr>
                    <td>${estagio.aluno.pessoa.nome}</td>
                    <td>${estagio.aluno.matricula}</td>
                    <td class="fw-bold">${tdHorasTotais(estagio.horasTotais)}</td>
                </tr>`
            }, '')
        }

        // Cria o TD das Horas Totais
        function tdHorasTotais(horasTotais){
            return `${doisDigitos(horasTotais.horas)}:${doisDigitos(horasTotais.minutos)}:${doisDigitos(horasTotais.segundos)}`
        }

        function doisDigitos(number){
            return number.toLocaleString('en-US', {minimumIntegerDigits: 2, useGrouping:false})
        }
    }
    
    function preparartabelaLogs(){

        const socket = new SockJS('/mywebsockets')
        const stompClient = Stomp.over(socket)
        stompClient.debug = null
        stompClient.connect({}, () => {
            stompClient.subscribe('/topic/leitura', () => {
                atualizarTabelaLogs()
            })
        })

        const tabelaLogs = document.querySelector('#tabela-logs>tbody')
        const btnAtualizar = document.querySelector('#btn-atualizar')
    
        // Atualiza a tabela 
        atualizarTabelaLogs()
        
        // Botão de Atualizar
        btnAtualizar.addEventListener('click', () => {
            atualizarTabelaLogs()
        })
    
        // Atualiza a tabela
        function atualizarTabelaLogs(){
            fetch('/logs/ultimos')
            .then(response => response.json())
            .then(logs => tabelaLogs.innerHTML = construirTabela(logs))
        }
    
        // Constroi a tabela
        function construirTabela(logs = []){
            return logs.reduce((prevTr, log) => {
                return prevTr += `<tr>
                    <td>${tdDataHora(log.dataHora)}</td>
                    <td>${log.localizacao}</td>
                    <td>${log.tipoMicro}</td>
                    <td>${tdModoOperacao(log.modoOperacao)}</td>
                    <td>${log.codigo}</td>
                    <td>${tdPessoa(log.pessoa, log.codigo)}</td>
                </tr>`
            }, '')
        }
    
        // Cria o TD da Data e Hora
        function tdDataHora(dataHora){
            const date = new Date(dataHora)
            return date.toLocaleString('pt-BR')
        }
    
        // Cria o TD do Modo de Operação
        function tdModoOperacao(modo){
            const modos = {
                'APENAS_LEITURA': 'Apenas Leitura',
                'CONTAGEM_HORAS': 'Contagem de Horas'
            }
            return modos[modo]
        }
    
        // Cria o TD de pessoa
        function tdPessoa(pessoa, codigo){
            return pessoa || `
                <a href="/app/alunos/cadastrar?codigo=${codigo}" class="btn btn-success btn-xs">
                    <svg width="18" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path opacity="0.4" d="M21.101 9.58786H19.8979V8.41162C19.8979 7.90945 19.4952 7.5 18.999 7.5C18.5038 7.5 18.1 7.90945 18.1 8.41162V9.58786H16.899C16.4027 9.58786 16 9.99731 16 10.4995C16 11.0016 16.4027 11.4111 16.899 11.4111H18.1V12.5884C18.1 13.0906 18.5038 13.5 18.999 13.5C19.4952 13.5 19.8979 13.0906 19.8979 12.5884V11.4111H21.101C21.5962 11.4111 22 11.0016 22 10.4995C22 9.99731 21.5962 9.58786 21.101 9.58786Z" fill="currentColor"></path>                                <path d="M9.5 15.0156C5.45422 15.0156 2 15.6625 2 18.2467C2 20.83 5.4332 21.5001 9.5 21.5001C13.5448 21.5001 17 20.8533 17 18.269C17 15.6848 13.5668 15.0156 9.5 15.0156Z" fill="currentColor"></path>                                <path opacity="0.4" d="M9.50023 12.5542C12.2548 12.5542 14.4629 10.3177 14.4629 7.52761C14.4629 4.73754 12.2548 2.5 9.50023 2.5C6.74566 2.5 4.5376 4.73754 4.5376 7.52761C4.5376 10.3177 6.74566 12.5542 9.50023 12.5542Z" fill="currentColor"></path>
                    </svg>
                    Cadastrar Aluno
                </a>
            `
        }
    }

})