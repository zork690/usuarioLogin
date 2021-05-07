
const SERVIDOR = "http://localhost:8080";

let correoInput = $("#correoInput");
let recuperarButton = $("#recuperarButton");

$(document).ready(()=>{
    recuperarButton.hide();
});

function recuperar(){
    url = "/recuperar";
    urlCompleta = SERVIDOR + url;
    obj = {
        
        correo: correoInput.val()
    }

    console.log("PIDIENDO RECUPERAR", obj);

}

correoInput.keyup(()=>{
    if(correoInput.val() != "")
        recuperarButton.show();
    else
        recuperarButton.hide();
});