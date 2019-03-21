<#import "parts/common.ftl" as c>

<@c.page>
    List of product

    <table class="table">
        <thead>
        <tr>
            <th scope="col">Id</th>
            <th scope="col">ProductName</th>
            <th scope="col">Category</th>
            <th scope="col">Price</th>
            <th scope="col">Quantity On Pallet</th>
            <th scope="col">Quantity In Warehouse</th>
            <th scope="col"> </th>
        </tr>
        </thead>
        <tbody>
        <#list products as product>
            <tr>
                <td>${product.id}</td>
                <td>${product.productName}</td>
                <td>${product.category}</td>
                <td>${product.price}</td>
                <td>${product.quantityOnPallet}</td>
                <td>${product.quantityInWarehouse}</td>
                <td><a href="/product/${product.id}">edit</a></td>
            </tr>
        </#list>
        </tbody>
    </table>

    <input type="hidden" value="${_csrf.token}" name="_csrf"/>

    <td><a href="/product/add">Add new product</a></td>
</@c.page>