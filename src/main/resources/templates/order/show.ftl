<html>
<#include "../parts/_header.ftl">
<body>
<div class="container ">
    <#include "../parts/navbar.ftl">
    <h1 align="center" class="display-4 mb-5">Order Detail</h1>

<#--Cart Detail Table-->
    <table class="table table-striped text-center">
        <thead>
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Category</th>
            <th scope="col">Width</th>
            <th scope="col">Depth</th>
            <th scope="col">Height</th>
            <th scope="col">Weight</th>
            <th scope="col">Price</th>
            <th scope="col">Quantity</th>
            <th scope="col">Subtotal</th>

        </tr>
        </thead>
        <tbody>
        <#list items as item>
        <tr>
            <td class="align-middle">${item.getProduct().getProductName()}</td>
            <td class="align-middle">${item.getProduct().getCategory()}</td>
            <td class="align-middle">${item.getProduct().getWidth()}</td>
            <td class="align-middle">${item.getProduct().getDepth()}</td>
            <td class="align-middle">${item.getProduct().getHeight()}</td>
            <td class="align-middle">${item.getProduct().getWeight()}</td>
            <td class="align-middle">${item.getProduct().getPrice()?string.currency}</td>
            <td class="align-middle">${item.getQuantity()}</td>
            <td class="align-middle">${(item.getProduct().getPrice() * item.getQuantity())?string.currency}</td>
        </tr>
        </#list>
        </tbody>
    </table>
</div>
<#--<#include "../partials/_footer.ftl">-->
</body>
</html>