let registrarButton = $("#registrarButton");
let usuarioInput = $("#usuarioInput");
let correoInput = $("#correoInput");
let url = "";
let urlCompleta = "";
let obj = null;

const SERVIDOR = "http://localhost:8080";

$(document).ready(()=>{
    registrarButton.hide();
});

function registrar(){
    url = "/registrar";
    urlCompleta = SERVIDOR + url;
    obj = {
        nombre: usuarioInput.val(),
        correo: correoInput.val()
    }

    console.log("ENVIANDO A REGISTRAR ...", obj);
    peticionAjax("POST", urlCompleta, obj).then((respuesta) => {
        console.log(respuesta);
    }).catch((error) => {
        console.log(error);
    });
}

function pedirCuenta(){
    console.log("MANDANDO A PEDIR CUENTA");
    window.location.href = "login.html";
}

usuarioInput.keyup(()=>{
    if(usuarioInput.val() !== "" && (correoInput.val() !== ""))
        registrarButton.show();
    else
        registrarButton.hide();

});

correoInput.keyup(()=>{
    if(usuarioInput.val() != "" && correoInput.val() != "")
        registrarButton.show();
    else
        registrarButton.hide();
});