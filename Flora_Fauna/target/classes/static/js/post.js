function Registro() {
    const tipo = document.getElementById('tipo').value;
    const nombre = document.getElementById('nombre').value;
    const lugar = document.getElementById('lugares').value;
    const fileInput = document.getElementById('imagen');
    const file = fileInput.files[0]; // Obtener el archivo seleccionado

    //Cookie
    const tokenValue = document.cookie.split('; ')
        .find(cookie => cookie.startsWith('token'))
        ?.split('=')[1];
    console.log(tipo, nombre, lugar)
    if (file) {
        const reader = new FileReader();
        reader.onload = function (event) {
            // Obtener solo los datos base64, eliminando el prefijo
            const base64String = event.target.result.split(',')[1];
            fetch('/api/post', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Cookie': tokenValue
                },
                body: JSON.stringify({ tipo, nombre, lugar, base64String })
            })
            .then(response => {
                // Manejar la respuesta aquí
            })
            .catch(error => {
                console.error('Error al enviar la solicitud:', error);
            });
        };

        reader.readAsDataURL(file); // Leer el contenido del archivo como una URL de datos (data URL)
    }
}
