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
    url = "/usuario";
    urlCompleta = SERVIDOR + url;
    obj = {
        username: $("#usuarioInput").val(),
        password: $("#claveInput").val()
    }

    console.log("ENVIANDO LOGIN ...", obj, $("#usuarioInput").val(), $("#claveInput").val());
    peticionAjax("GET", urlCompleta, obj, $("#usuarioInput").val(), $("#claveInput").val()).then((respuesta) => {
        console.log(JSON.parse(respuesta));
        
        procesarRespuesta(JSON.parse(respuesta));


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

function procesarRespuesta(respuesta){
    console.log("PROCESANDO RESPUESTA...",respuesta);
    setResuesta("cookieUsuario", JSON.stringify(respuesta));
    window.location.href="../cambiarClaveComponent/cambiarClave.html"
}

function setResuesta(c_name,value) {
   localStorage.setItem(c_name, value);
   console.log("COOKIE ",getRespuesta(c_name));
}

function getRespuesta(cname) {
    return JSON.parse(localStorage.getItem(cname));
}