<#import "parts/common.ftl" as c>

<@c.page>
    List of product
    <form method="post" action="/cart">
    <table class="table">
        <thead>
        <tr>
            <th scope="col">ProductName</th>
            <th scope="col">Price</th>
            <th scope="col">Width</th>
            <th scope="col">Depth</th>
            <th scope="col">Height</th>
            <th scope="col">Weight</th>
            <th scope="col">Quantity On Pallet</th>
            <th scope="col">Quantity In Warehouse</th>
            <th scope="col"> </th>
        </tr>
        </thead>
        <tbody>
        <#list products as product>
            <tr data-id="${product.id}">
                <td>${product.productName}</td>
                <td><#setting locale = "en_US">${product.price?string("#,##0.00")}</td>
                <td>${product.width}</td>
                <td>${product.depth}</td>
                <td>${product.height}</td>
                <td>${product.weight}</td>
                <td>${product.quantityOnPallet}</td>
                <td>${product.quantityInWarehouse}</td>
                <td>
                    <button type="submit" class="btn btn-primary"> Add to Cart</button>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
    <input type="hidden" value="${_csrf.token}" name="_csrf"/>
    </form>
</@c.page>