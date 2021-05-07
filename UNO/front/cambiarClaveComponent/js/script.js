let claveInput = $("#claveInput");
let url = "";
let urlCompleta = "";
let obj = null;
let respuesta = null;
let cookieName = "cookieUsuario";
let usuario = "";
let pass = "";

const SERVIDOR = "http://localhost:8080";

$(document).ready(()=>{
    pintarUsuario();
});

function pintarUsuario(){
 respuesta = getRespuesta(cookieName);
 console.log("TENEMOS DATOS: ",respuesta);
 setearDatos();
}

function setearDatos(){
    usuario = respuesta.username;
    pass = respuesta.clave;
}


function getRespuesta(cname) {
    return JSON.parse(localStorage.getItem(cname));
}

function resetearClave(){
    url = "/cambiar";
    urlCompleta = SERVIDOR + url;
    obj = {

        clave: claveInput.val()
    }

    console.log("RESETEANDO LOGIN ...", obj);
    peticionAjax("POST", urlCompleta, obj, usuario, pass).then((respuesta) => {
        console.log(JSON.parse(respuesta));

        procesarRespuesta(JSON.parse(respuesta));

    }).catch((error) => {
        console.log(error);
    });
}

function procesarRespuesta(respuesta){
    console.log("PROCESANDO RESPUESTA...",respuesta);
    setResuesta("cookieUsuario", JSON.stringify(respuesta));
    window.location.href="../bienvenidaComponent/bienvenida.html"
}

function setResuesta(c_name,value) {
   localStorage.setItem(c_name, value);
   console.log("COOKIE ",getRespuesta(c_name));
}