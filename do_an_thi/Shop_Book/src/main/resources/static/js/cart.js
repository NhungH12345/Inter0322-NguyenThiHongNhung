const api = 'http://localhost:8080/cart';
const container = document.querySelector(('.col-sm-9.padding-right'));

function handlePromise(promise) {
    promise
        .then(result => {
            if (!result.ok) {
                throw new Error("không đủ hàng")
            }
            return result.json();
        })
        .then(json => {
            toastr.success(json.msg)
        })
        .catch(error => {
            toastr.error(error)
        })
}

function addToCart(idProduct) {
    const quantity = document.querySelector('#product-quantity')
    handlePromise(
        fetch(`${api}/create`, {
            method: 'POST',
            headers: {
                'content-type': 'application/json'
            },
            body: JSON.stringify({
                quantity: parseInt(quantity.value),
                product: {
                    id: idProduct
                }
            })
        }
    ))
}
function increaseQuantity() {
    const btnIncreases = document.querySelectorAll('#btn-increase');
    btnIncreases.forEach(btnIncrease => {
        btnIncrease.addEventListener('click', event => {
            const {id} = event.target.dataset;
            fetch(`${api}/increase`, {
                method: 'POST',
                headers: {
                    'content-type': 'application/json'
                },
                body: JSON.stringify({
                    id
                })
            })
                .then(result => result.text())
                .then(html => {
                    container.innerHTML = html;
                    deleteItem();
                    increaseQuantity();
                    decreaseQuantity();
                })
                .catch(error => alert(error))
        })
    })
}


function decreaseQuantity() {
    const btnDecreases = document.querySelectorAll('#btn-decrease');
    btnDecreases.forEach(btnDecrease => {
        btnDecrease.addEventListener('click', event => {
            const {id} = event.target.dataset;
            fetch(`${api}/decrease`, {
                method: 'POST',
                headers: {
                    'content-type': 'application/json'
                },
                body: JSON.stringify({
                    id
                })
            })
                .then(result => result.text())
                .then(html => {
                    container.innerHTML = html;
                    deleteItem();
                    increaseQuantity();
                    decreaseQuantity();
                })
                .catch(error => alert('that bai'))
        })
    })
}

function deleteItem() {
    const btnDeletes = document.querySelectorAll('#btn-delete')
    btnDeletes.forEach(btnDelete => {
        btnDelete.addEventListener('click', event => {
            const {id} = event.target.dataset;
            fetch(`${api}/delete/${id}`)
                .then(result => result.text())
                .then(html => {
                    container.innerHTML = html;
                    deleteItem();
                    increaseQuantity();
                    decreaseQuantity();
                })
                .catch(alert)
        })
    })
}

function removeAll() {
    fetch(`${api}/remove-all`)
        .then(result => result.text())
        .then(html => {
            container.innerHTML = html;
            deleteItem();
            increaseQuantity();
            decreaseQuantity();
        })
        .catch(alert)
}

deleteItem();
increaseQuantity();
decreaseQuantity();