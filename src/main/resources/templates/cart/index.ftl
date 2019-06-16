<html xmlns="http://www.w3.org/1999/html">
<#include "../parts/_header.ftl">
<#setting locale="en_US">
<body>
<div class="container ">
    <#include "../parts/navbar.ftl">
    <h1 align="center" class="display-4 mb-5">My Cart</h1>

<#--Cart Detail Table-->
    <table class="table table-striped text-center">
        <thead>
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Price</th>
            <th scope="col">Quantity</th>
            <th scope="col">Subtotal</th>
            <th scope="col">Action</th>

        </tr>
        </thead>
        <tbody>
        <#list items as item>
        <tr>
            <td class="align-middle">${item.getProductName()}</td>
            <td class="align-middle">${item.getPrice()?string.currency}</td>
            <td class="align-middle">
                <a href="/cart/change?product_id=${item.getId()}&quantity=${item.getQuantity()-1}"><i
                        class="fas fa-minus"></i></a>
                <input min="1" id="${item.getId()}"
                       max="${(product.getQuantityInWarehouse())!"1"}" type="text" size="5" value="${item.getQuantity()}"
                       name='count' onkeyup="change(this)">
                <a href="/cart/change?product_id=${item.getId()}&quantity=${item.getQuantity()+1}">
                    <i class="fas fa-plus"></i></a>
            </td>
            <td class="align-middle">${(item.getPrice() * item.getQuantity())?string.currency}</td>
            <td class="align-middle">
                <a href="/cart/remove?product_id=${item.getId()}">Remove</a>
            </td>

        </tr>
        </#list>
        </tbody>
    </table>

<#--Check Out -->
    <#if items?has_content >
        <div>
            <h5 style="display: inline;">Total: ${total?string.currency}</h5>
            <form action ="/cart/checkout" method="post">
                <button type="submit" class="btn btn-warning float-right">Checkout</button>
                <input type="text" name="address" id="address" placeholder="Enter Address" required/>
                <input type="hidden" value="${_csrf.token}" name="_csrf"/>
            </form>
        </div>
    <#else>
    <#--Empty Cart-->
        <div>
            <h4 class="text-muted text-center">Cart is empty. Go to get something! :)</h4>
        </div>
    </#if>



</div>
</body>
<script>
    var timeout = null;
    function change(element)
    {   clearTimeout(timeout);
        var quantity = element.value;
        var id = element.id;
        var theUrl = "/cart/change?product_id=" + id + "&quantity=" + quantity;
        timeout = setTimeout(function(){location.href = theUrl} , 1000);
    }
</script>
</html>