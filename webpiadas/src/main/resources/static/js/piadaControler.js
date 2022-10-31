function show()
{
    fetch("/apis/listar-todos")
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

        var resp="";
        for (let i=0;i<data.length;i++)
            resp+=`<option value="${data[i].id}">${data[i].nome}</option>`;        
        document.getElementById("categoria").innerHTML=resp;
    }
    
}
window.onload = function(){show();};

async function gravarPiada()
{
    
    var data = JSON.stringify(Object.fromEntries(new FormData(fdados)));
    let response = await fetch("/apis/piada",{headers: {'Accept': 'application/json','Content-Type': 'application/json'}, method: 'POST', body: data});
    let userData = await response.text();
    return userData; // não é necessário o await no return
}