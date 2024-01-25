document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('loginBtn').addEventListener('click', enviarFormulario);
});

function enviarFormulario() {
    const username = document.getElementById('usuario').value;
    const password = document.getElementById('contrasena').value;

    fetch('/auth/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ username, password })
    })
        .then(res => {
            if (res.ok) {
                return res.text();
            } else {
                openModalERROR()
            }
        })
        .then(data => {
            if (data == 'Usuario') {
                window.location.href = "/";
            } else if (data == 'Biologo') {
                window.location.href = "/Profesor";
            } else if (data == 'Administrador') {
                window.location.href = "/Administrador";
            } else {
                openModalERROR()
            }
        })
        .catch(error => {
            console.error('Error de red', error);
        });
}

function openModalERROR() {
    document.getElementById('NosuccessModal').classList.remove('hidden');
}

function closeModalERROR() {
    document.getElementById('NosuccessModal').classList.add('hidden');
}