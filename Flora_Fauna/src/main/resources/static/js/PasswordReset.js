function Olvidar() {
    const email = document.getElementById('correo').value;
    fetch('/auth/password-reset-request?mail=' + email, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ email })
    })
        .then(response => {
            if (response.ok) {
                return response.text();
            }
            throw new Error('Error en la solicitud');
        })
        .then(data => {
            if(data == 'Correo incorrecto') {
                openModalPassword()
            } else if (data == 'Toke No Vencido') {
                openModalEmail()
            } else {
                setTimeout(() => {
                    window.location.href = "/login";
                }, 4500)
            }
        })
        .catch(error => {
            console.error('Error de red:', error);
        });
}

function CambioContra() {
    let currentUrl = window.location.href;
    let url = new URL(currentUrl);
    const token = url.searchParams.get("token");
    const password = document.getElementById('contrasena1').value;
    const password2 = document.getElementById('contrasena2').value;
    if (password == password2) {
        fetch('/auth/reset-password-password', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ token, password })
        })
            .then(response => {
                if (response.ok) {
                    openModalBien()
                    setTimeout(() => {
                        window.location.href = "/login";
                    }, 4500);
                }
                throw new Error('Error en la solicitud');
            })
            .catch(error => {
                console.error('Error de red:', error);
            });
    } else {
        openModalPassword()
    }
}

function openModalBien() {
    document.getElementById('contrabien').classList.remove('hidden');
}

function openModalPassword() {
    document.getElementById('contramal').classList.remove('hidden');
}

function openModalEmail() {
    document.getElementById('emailmal').classList.remove('hidden');
}
