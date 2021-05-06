let loginButon = $("#loginButon");
let usuarioInput = $("#usuarioInput");
let claveInput = $("#claveInput");
let url = "";
let urlCompleta = "";
let obj = null;

const SERVIDOR = "http://localhost:8080";

$(document).ready(()=>{
    loginButon.hide();
});

function ingresar(){
    console.log("dfdsfsdkl");
    url = "/ingresar";
    urlCompleta = SERVIDOR + url;
    obj = {
        username: $("#usuarioInput").val(),
        clave: $("#claveInput").val()
    }

    console.log("ENVIANDO LOGIN ...", obj);
    peticionAjax("POST", urlCompleta, obj).then((respuesta) => {
        console.log(respuesta);
    }).catch((error) => {
        console.log(error);
    });
}

function pedirClave(){
    console.log("MANDANDO A RECUPERAR CLAVE");
    window.location.href = "recuperarClave.html";
}

usuarioInput.keyup(()=>{
    if(usuarioInput.val() !== "" && (claveInput.val() !== ""))
        loginButon.show();
    else
        loginButon.hide();

});

claveInput.keyup(()=>{
    if(usuarioInput.val() != "" && claveInput.val() != "")
        loginButon.show();
    else
        loginButon.hide();
});