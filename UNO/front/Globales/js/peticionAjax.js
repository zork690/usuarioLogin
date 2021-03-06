let objeto_ajax = crear_objeto_XMLHttpRequest();

function peticionAjax(metodo, url, parametros=null, usuario, clave) {
    return new Promise((resuelve, rechaza) => {
        objeto_ajax.onreadystatechange = () => {
            if ((objeto_ajax.readyState == 4) && objeto_ajax.status == 200) {
                resuelve(objeto_ajax.responseText);
            }

            if((objeto_ajax.readyState == 4) && objeto_ajax.status == 401)
                rechaza("BAD CREDENTIALS");

            if((objeto_ajax.readyState == 4) && (objeto_ajax.status != 200)){
                rechaza("ERROR PIDIENDO LA INFO  AL SERVIDOR...", objeto_ajax.statusText);
            }


            
        };
        objeto_ajax.open(metodo, url, true);
        objeto_ajax.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        //objeto_ajax.setRequestHeader('Access-Control-Allow-Origin', '*');
        //objeto_ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');
        objeto_ajax.setRequestHeader("Authorization", "Basic " + btoa(usuario + ":" + clave));
        objeto_ajax.send(JSON.stringify(parametros));
        //objeto_ajax.send("username=John&password=Smith&grant_type=password");
    });
}