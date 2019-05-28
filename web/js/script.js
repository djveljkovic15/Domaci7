'use strict';

function loadCoupons() {

    //AJAX
    var xhttp = new XMLHttpRequest();

    /*
    onreadystatechange.readyState
        0: request not initialized
        1: server connection established
        2: request received
        3: processing request
        4: request finished and response is ready
     */

    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            var result = JSON.parse(this.response);

            // Dohvati tabelu iz DOM-a po id-u
            var table = document.getElementById("coupons-tbl");

            // Dohvati prvi tbody
            var oldTBody = table.tBodies[0];
            var newTBody = document.createElement("tBody");

            for (var i = 0; i < result.length; i++) {

                var bRow = document.createElement("tr"); // Kreiraj novi red

                // Postavi vrednosti za taj red iz rezultata sa servera
                var tdCouponName = document.createElement("td");
                tdCouponName.innerHTML = result[i].couponName;
                var tdShopName = document.createElement("td");
                tdShopName.innerHTML = result[i].shop.shopName;
                var tdDiscountPrice = document.createElement("td");
                tdDiscountPrice.innerHTML = result[i].discountPrice;
                var tdOriginalPrice = document.createElement("td");
                tdOriginalPrice.innerHTML = result[i].originalPrice;
                var tdDiscount = document.createElement("td");
                tdDiscount.innerHTML =
                    (100 / (result[i].originalPrice*1.0 / (result[i].originalPrice-result[i].discountPrice))).toFixed(2)+"%";
                var tdBtnDelete = document.createElement("td");
                var btnDelete = document.createElement("button");
                btnDelete.innerText = "Delete";

                btnDelete.onclick = (function(id, btn) {
                    return function() {
                        // console.log(btn);
                        // console.log(btn.parentNode);
                        // console.log(btn.parentNode.parentNode);
                        document.getElementById("coupons-tbl").deleteRow(btn.parentNode.parentNode.rowIndex);
                        deleteCoupon(id);
                    }})(result[i].id, btnDelete);

                bRow.appendChild(tdCouponName);
                bRow.appendChild(tdShopName);
                bRow.appendChild(tdDiscountPrice);
                bRow.appendChild(tdOriginalPrice);
                bRow.appendChild(tdDiscount);
                tdBtnDelete.appendChild(btnDelete);
                bRow.appendChild(tdBtnDelete);
                newTBody.appendChild(bRow)
            }

            // Zameni postojeci sa novim tBody-jem.
            // Na taj nacin ce uvek da se ispisuju svezi elementi a stari da nestanu.
            table.replaceChild(newTBody, oldTBody)
        }
    };

    xhttp.open("GET", "/dzo/coupons", true);
    xhttp.send();
}
function loadShops() {

    //AJAX
    var xhttp = new XMLHttpRequest();

    /*
    onreadystatechange.readyState
        0: request not initialized
        1: server connection established
        2: request received
        3: processing request
        4: request finished and response is ready
     */

    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            var result = JSON.parse(this.response);

            // Dohvati label iz DOM-a po id-u
            // var label = document.getElementById("shops-drop");

            // Dohvati prvi select
            // var oldSelect = document.getElementById("shops-select");
            var newSelect = document.getElementById("shopName");
            for (var i = 0; i < result.length; i++) {

                // Postavi vrednosti za taj option iz rezultata sa servera
                var opShopName = document.createElement('option');
                opShopName.innerHTML = result[i].shopName;

                newSelect.appendChild(opShopName);
            }
        }
    };

    xhttp.open("GET", "/dzo/shops", true);
    xhttp.send();
}

function deleteCoupon(id){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {

        }
    };
    xhttp.open("DELETE", "/dzo/coupons/delete/" + id, true);
    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.send(JSON.stringify({id:id}));
}

window.onload = function () {
    loadCoupons();
    loadShops();
};

// Zakaci event listener na klik dugmeta
document.getElementById("load-coupons-btn").addEventListener("click", loadCoupons);

/*
Ovde ce se nalaziti logika koja se pozvati prilikom submit-a forme.
 */
function processForm(e) {
    if (e.preventDefault) e.preventDefault();
    var formData = new FormData(e.target);

    var couponName = formData.get("couponName");
    var shopName = formData.get("shopName");
    var discountPrice = formData.get("discountPrice");
    var originalPrice = formData.get("originalPrice");

    const xHttp = new XMLHttpRequest();

    xHttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            const shop = JSON.parse(this.response);
            createCoupon(couponName, shop, discountPrice, originalPrice);
        }
    };
    xHttp.open("GET", "/dzo/shops/name/" + shopName);
    xHttp.send();
    // Obavezno vratiti false da bi se pregazilo default-no ponasanje prilikom submit-a.
    return false;
}
function createCoupon(couponName, shop, discountPrice, originalPrice) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            loadCoupons()
        }
    };

    xhttp.open("POST", "/dzo/coupons/add", true);
    xhttp.setRequestHeader("Content-Type", "application/json");

    // Saljemo objekat sa parametrima pretvoren u JSON
    xhttp.send(JSON.stringify({couponName: couponName, shop:shop,
        discountPrice:discountPrice, originalPrice:originalPrice}));
}

var form = document.getElementById('add-coupon-form');
if (form.attachEvent) {
    // IE support
    form.attachEvent("submit", processForm);
} else {
    form.addEventListener("submit", processForm);
}

// Dohvatanje query parametara iz url-a
var urlParams = new URLSearchParams(window.location.search);
var myParam = urlParams.get('myParam');
console.log(myParam);
