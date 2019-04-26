<html>
<#include "../parts/_header.ftl">
<body>
<div class="container  ">
    <#--<#include "../partials/_nav.ftl">-->
    <h1 align="center" class="display-4 mb-5">Product Detail</h1>
    <div class="row text-center justify-content-center">
        <div class="col-lg-6 ">
            <div class="card mb-4 ">
                <#--<img height="60%" class="card-img-top" src="${product.getProductIcon()}">-->
                <div class="card-body">
                    <h4 class="card-title ">${product.getProductName()}</h4>
                    <form method="post" action="/cart">
                        <div class="text-left">
                            <input hidden name="productId" value="${product.getId()}">
                            <p class="card-test"><strong>Width: </strong>${product.getWidth()}
                            <p class="card-test"><strong>Height: </strong>${product.getHeight()}
                            <p class="card-test"><strong>Depth: </strong>${product.getDepth()}
                            <p class="card-test"><strong>Weight: </strong>${product.getWeight()}
                            </p>
                            <p class="card-text">
                                <strong>Price: </strong>
                                <label id="price"
                                       value="${product.getProductName()}">${product.getPrice()?string.currency}</label>
                            </p>
                            <p class="card-text"><strong>Stock: </strong>${product.getQuantityInWarehouse()}</p>

                            <label class="card-text" for="quantity">
                                <strong>Quantity: </strong>
                            </label>
                            <input type="number"
                                   id="quantity"
                                   name="quantity"
                                   value="1"
                                   min="1"
                                   max="${product.getQuantityInWarehouse()}"
                                   oninput="changeSubtotal()">
                            <p class="card-text"><strong>Subtotal: </strong>
                            <#--For JavaScript inHTML-->
                                <label id="subtotal">${(product.getPrice())?string.currency}</label>
                            </p>
                        </div>
                        <button type="submit"
                                class="btn btn-primary btn-lg <#if product.getProductStatus()==1>disabled</#if>">
                            Add to Cart
                        </button>
                        <input type="hidden" value="${_csrf.token}" name="_csrf"/>
                    </form>

                </div>
            </div>
        </div>
    </div>
</div>
</body>
<#--<#include "../partials/_footer.ftl">-->
<script>
    var price_e = document.getElementById("price");
    var subtotal_e = document.getElementById("subtotal");
    var quantity_e = document.getElementById("quantity");
    var price = parseFloat(document.getElementById("price").innerText.substr(1));
    var unit = price_e.innerHTML.charAt(0);

    function changeSubtotal() {
        var subtotal = (parseFloat(quantity_e.value) * price).toFixed(2);
        subtotal_e.innerHTML = unit + subtotal;

    }
</script>
</html>