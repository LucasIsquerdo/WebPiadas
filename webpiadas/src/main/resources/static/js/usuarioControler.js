async function gravarUsuario()
{
    var data = JSON.stringify(Object.fromEntries(new FormData(form)));
    let response = await fetch("/apis/usuario",{headers: {'Accept': 'application/json','Content-Type': 'application/json'}, method: 'POST', body: data});
    let userData = await response.text();
    return userData; // não é necessário o await no return
}

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


function logar() 
{
    var login = document.getElementById("uemail").value;
    var senha = document.getElementById("usenha").value;
    
    const URL_TO_FETCH = `/security/autenticar?login=${login}&senha=${senha}`;
    const data = new URLSearchParams();   
    for (const pair of new FormData(document.getElementById('fdados'))) {
     data.append(pair[0], pair[1]);
    }
    fetch(URL_TO_FETCH, {method: 'post', body: senha })
     .then(response=>{ if(response.ok) return response.text(); else throw Error("Erro ao fazer login") })
     .then(text => {alert("Logado com Sucesso");  window.location.href = "index.html"; localStorage.setItem("token", text);})
     .catch(err => alert("Dados inválidos")) 
    event.preventDefault("fdados");
}
