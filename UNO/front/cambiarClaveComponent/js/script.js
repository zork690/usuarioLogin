let loginButon = $("#loginButon");
let usuarioInput = $("#usuarioInput");
let claveInput = $("#claveInput");
let url = "";
let urlCompleta = "";
let obj = null;

const SERVIDOR = "http://localhost:8080";

function getRespuesta(cname) {
    return JSON.parse(localStorage.getItem(cname));
}