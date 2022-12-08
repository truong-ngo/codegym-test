function openEditModal(id, name, email, address, phoneNumber, salary, department) {
    document.getElementById("e-id-container").innerHTML = id;
    document.getElementById("e-id").value = id;
    document.getElementById("e-name").value = name;
    document.getElementById("e-email").value = email;
    document.getElementById("e-address").value = address;
    document.getElementById("e-phone_number").value = phoneNumber;
    document.getElementById("e-salary").value = salary;
    let focusElements = document.getElementsByClassName("focus");
    for (let focusElement of focusElements) {
        focusElement.removeAttribute("selected");
    }
    document.getElementById("focus-" + department).setAttribute("selected", true);
}

function openDeleteModal(id) {
    document.getElementById("d-id-container").innerHTML = id;
    document.getElementById("d-id-body").innerHTML = id;
    document.getElementById("d-id").value = id;
}