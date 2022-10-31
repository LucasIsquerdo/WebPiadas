// function pesquisar()
// {
    
//     var filtro = document.getElementById("busca").value
//     fetch("/apis/listar-todas-piadas?filtro="+filtro)
//     .then(function (response) {
//         return response.json();
//     })
//     .then(function (data) {
//         appendData(data);
//     })
//     .catch(function (err) {
//         console.log('error: ' + err);
//     });
//     function appendData(data) {

//         var table="";
//         table+=`<tr><th>Titulo</th><th>Texto</th></tr>`
//         for (let i=0;i<data.length;i++)
//                 table+=`<tr><td>${data[i].titulo}</td><td>${data[i].texto}</td></tr>`;        
//             document.getElementById("respesq").innerHTML=table;
      
//     }
// }

function verificar()
{
    const URL_TO_FETCH = '/apis/testar-acesso';
    var status;
    fetch(URL_TO_FETCH, {method: 'POST',
       headers:{'Authorization':`${localStorage.getItem("token")}`,}})
    .then(response=> response.text())
    .then(result=> {
        if(result.includes("Piada"))
            window.location.href="cadastroPiadas.html"
        else
        {
            alert("Você precisa estar logado para acessar o recurso!")
            window.location.href = "login.html";
        }
    })
    .catch(function (err) {
        console.log(err)
    });
}

function pesquisar()
{
    
    var filtro = document.getElementById("busca").value
    const URL_TO_FETCH = '/apis/testar-acesso';
    var status;
    fetch(URL_TO_FETCH, {method: 'POST',
       headers:{'Authorization':`${localStorage.getItem("token")}`,}})
    .then(response=> response.text())
    .then(result=> 
        {
            if(result.includes("Piada"))
            {
                fetch("/apis/listar-todas-piadas?filtro="+filtro)
                .then(function (response) {
                    return response.json();
                })
                .then(function (data) {
                    appendData(data);
                })
                .catch(function (err) {
                    console.log('error: ' + err);
                });
                function appendData(data) {

                    var table="";
                    table+=`<tr><th>Titulo</th><th>Texto</th><th>Curtir</th></tr>`
                    for (let i=0;i<data.length;i++)
                            table+=`<tr>
                            <td>${data[i].titulo}</td>
                            <td>${data[i].texto}</td>
                            <td><img width="100px" src='imagens/smile.png' onclick='UpdateRanking(${data[i].id})'>
                            </td>
                            </tr>`;        
                        document.getElementById("respesq").innerHTML=table;
                }
            }
        })
    .catch(err=> console.error(err));
    //<img width="100px" src='imagens/smile.png' 
    // <input onclick='UpdateRanking(${data[i].id})'type="submit"></input>
}
function UpdateRanking(id)
{
    fetch("/apis/update?id="+id)
    .then(function (response) {
        return response.json();
    })
    .then(function (text) {
        alert("Você curtiu essa piada!")
        window.location.href = "index.html";
    })
    .catch(function (err) {
        console.log('error: ' + err);
    });
}
function PiadaDoDia()
{
    const URL_TO_FETCH = '/apis/testar-acesso';
    var status;
    fetch(URL_TO_FETCH, {method: 'POST',
       headers:{'Authorization':`${localStorage.getItem("token")}`,}})
    .then(response=> response.text())
    .then(result=> 
        {
            if(result.includes("Piada"))
            {
                fetch("/apis/listar-todas-piadas?filtro="+"")
                .then(function (response) {
                    return response.json();
                })
                .then(function (data) {
                    appendData(data);
                })
                .catch(function (err) {
                    console.log('error: ' + err);
                });
                function appendData(data) {
                    var conteudo = document.getElementById("conteudo");
                    var titulo = document.getElementById("titulo")  
                    const dia = new Date().getDay();
                    if(dia<=data.length)
                    {   
                        conteudo.innerHTML = data[dia].texto;
                        titulo.innerHTML =  '"'+data[dia].titulo+'" :';
                    }
                    else
                    {    
                        titulo.innerHTML="Ops!"
                        conteudo.innerHTML="Piada Inexistente"
                    }

                    
                }
            }
            else
            {
                var conteudo = document.getElementById("conteudo");
                var titulo = document.getElementById("titulo")  
                titulo.innerHTML="ERRO!"
                conteudo.innerHTML="Faça Login para visualizar"
            }

        }
        )
    .catch(err=> console.error(err));
    
}

