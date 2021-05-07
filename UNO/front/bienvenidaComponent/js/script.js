let respuesta = null;
let cookieName = "cookieUsuario";
let usuario = "";
let pass = "";

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