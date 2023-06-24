const addProduct = () => {
    let name = document.getElementById("name").value.trim();
    let price = document.getElementById("price").value.trim();

    if (name == "") {
        alert("Please enter the product name !");
    } else if (price == 0 || price == isNaN) {
        alert("Please enter the product price !");
    } else {
        let request = new XMLHttpRequest();

        request.onload = () => {
            let json = JSON.parse(request.responseText);

            alert("Product item added successfully !");

            document.getElementById("name").value = "";
            document.getElementById("price").value = "";

            let tableBody = document.getElementById("product-table-body");
            tableBody.innerText = "";

            for (let i = 0; i < json.length; i++) {
                let item = json[i];

                let trElement = document.createElement('tr');

                let indexTd = document.createElement('td');
                indexTd.textContent = i + 1;
                trElement.appendChild(indexTd);

                let nameTd = document.createElement('td');
                nameTd.textContent = item.name;
                trElement.appendChild(nameTd);

                let priceTd = document.createElement('td');
                priceTd.textContent = item.price;
                trElement.appendChild(priceTd);

                tableBody.appendChild(trElement);
            }
        }

        request.open("GET", "../addproductprocessing?name=" + name + "&price=" + price, true);
        request.send();
    }
}

let searchText = document.getElementById("search-item");

let isFocused = false;
let hasInput = false;

searchText.addEventListener("focus", (evt) => {
    isFocused = true;
    searchItem();
});

searchText.addEventListener("keyup", (evt) => {
    hasInput = evt.target.value.trim().length > 0;
    searchItem();
});

const searchItem = () => {

    let orderedListElement = document.getElementById("item-list");

    if (isFocused && hasInput) {

        let request = new XMLHttpRequest();
        request.onload = () => {
            let json = JSON.parse(request.responseText);

            orderedListElement.innerHTML = "";

            for (let i = 0; i < json.length; i++) {
                let item = json[i];

                let itemId = item.id;

                let listTg = document.createElement("li");
                listTg.setAttribute('onclick', 'addToInvoiceTable(' + itemId + ')');
                listTg.id = itemId;
                listTg.style.cursor = "pointer";
                listTg.textContent = item.name;
                orderedListElement.appendChild(listTg);
            }
        }

        request.open("GET", "../searchproductprocessing?text=" + searchText.value, true);
        request.send();
    } else {
        orderedListElement.innerText = "";
    }
}

const addToInvoiceTable = (id) => {
    let tableBody = document.getElementById("invoice-table-body");

    if (tableBody.rows.length > 0) {

        let isDuplicate = false;

        for (let i = 0; i < tableBody.rows.length; i++) {

            let row = tableBody.rows[i];

            if (row.id == id) {
                isDuplicate = true;
                break;
            }
        }

        if (!isDuplicate) {
            addItem();
        } else {
            alert("Duplicate entry !")
        }

    } else {
        addItem();
    }

    function addItem() {
        let request = new XMLHttpRequest();

        request.onload = () => {

            let json = JSON.parse(request.responseText);

            let trElement = document.createElement('tr');
            trElement.id = json.id;

            let indexTd = document.createElement('td');
            indexTd.textContent = tableBody.rows.length + 1;
            trElement.appendChild(indexTd);

            let nameTd = document.createElement('td');
            nameTd.textContent = json.name;
            trElement.appendChild(nameTd);

            let priceTd = document.createElement('td');
            priceTd.textContent = json.price;
            trElement.appendChild(priceTd);

            tableBody.appendChild(trElement);
        }

        request.open("GET", "../additemtoinvoicetableproccesing?id=" + id, true);
        request.send();
    }
}

function checkout() {
    let tableBody = document.getElementById("invoice-table-body");

    let itemIDs = [];

    if (tableBody.rows.length > 0) {
        for (let i = 0; i < tableBody.rows.length; i++) {
            let row = tableBody.rows[i];
            itemIDs.push(row.id);
        }

    } else {
        alert("No items to checkout !")
    }

    let request = new XMLHttpRequest();

    request.onload = () => {
        if (request.responseText == 3131231) {
            alert("Checkout done !")
            tableBody.innerHTML = "";
            document.getElementById("item-list").innerText = "";
            document.getElementById("search-item").value = "";
        }
    }

    request.open("POST", "../checkout", true);
    request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    request.send("ids=" + itemIDs);
}

