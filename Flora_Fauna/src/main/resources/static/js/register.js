document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('registrarBtn').addEventListener('click', enviarRegistro);
});

function enviarRegistro() {
    const username = document.getElementById('usuario').value;
    const password = document.getElementById('contrasena').value;
    const correo = document.getElementById('correo').value;
    const cedula = document.getElementById('cedula').value;
    const pri_nom = document.getElementById('nombre').value;
    const pri_ape = document.getElementById('apellido').value;
    console.log(username)
    fetch('/auth/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ username, password, correo, cedula, pri_nom, pri_ape })
    })
        .then(res => {
            if (res.ok) {
                return res.json();
            }
            throw new Error('Error en la solicitud');
        })
        .then(data => {
            if (data == -1) {
                openModalERROR()
            } else {
                openModal();
                setTimeout(() => {
                    window.location.href = "/login";
                }, 4500);
            }
        })
        .catch(error => {
            console.error('Error de red:', error);
        });
}


function openModal() {
    document.getElementById('successModal').classList.remove('hidden');
}

function closeModal() {
    document.getElementById('successModal').classList.add('hidden');
}

function openModalERROR() {
    document.getElementById('NosuccessModal').classList.remove('hidden');
}

function closeModalERROR() {
    document.getElementById('NosuccessModal').classList.add('hidden');
}