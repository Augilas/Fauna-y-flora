async function obtenerComentarios() {
    try {
        const response = await fetch('http://localhost:8080/comentarios/animales');

        if (!response.ok) {
            throw new Error('Error al obtener los comentarios: ' + response.statusText);
        }

        const data = await response.json();

        const contenedorCartas = document.getElementById('contenedor-cartas');

        data.forEach(function(animal) {

            const carta = document.createElement('div');
            carta.classList.add('bg-white', 'p-4', 'rounded', 'shadow', 'mb-4');

            const titulo = document.createElement('h1');
            titulo.classList.add('text-xl', 'font-semibold', 'mb-2');
            titulo.textContent = animal.nombre;

            carta.appendChild(titulo);

            const contenedorComentarios = document.createElement('div');
            contenedorComentarios.classList.add('space-y-2');

            if (animal.comentarios) {
                const comentarios = JSON.parse(animal.comentarios);
                comentarios.forEach(function(comentario) {

                    const comentarioElemento = document.createElement('div');
                    comentarioElemento.classList.add('text-gray-700');
                    comentarioElemento.textContent = comentario.Nombre + ': ' + comentario.Comentario;


                    contenedorComentarios.appendChild(comentarioElemento);
                });
            } else {

                const sinComentarios = document.createElement('div');
                sinComentarios.classList.add('text-gray-700');
                sinComentarios.textContent = 'No hay comentarios para este animal.';
                

                contenedorComentarios.appendChild(sinComentarios);
            }


            carta.appendChild(contenedorComentarios);


            contenedorCartas.appendChild(carta);
        });

    } catch (error) {
        console.error('Error:', error);
    }
}