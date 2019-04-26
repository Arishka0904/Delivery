<#include "security.ftl">
<#import "login.ftl" as l>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Delivery</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
        <#if isAdmin>
            <li class="nav-item">
                <a class="nav-link" href="/user" id="users">User list</a>
            </li>
        </#if>
        <#if isAdmin>
            <li class="nav-item">
                <a class="nav-link" href="/product" id="products">Products</a>
            </li>
        </#if>
        <#if user??>
            <li class="nav-item">
                <a class="nav-link" href="/user/profile">Profile</a>
            </li>
        </#if>
        <li class="nav-item">
            <a class="nav-link" href="/category/FIRST_CATEGORY">FIRST_CATEGORY</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/category/SECOND_CATEGORY">SECOND_CATEGORY</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/category/THIRD_CATEGORY">THIRD_CATEGORY</a>
        </li>

        </ul>

        <#if !user?? || !user.isAdmin()  >
        <div class="navbar-text mr-3">
            <a class="nav-link " href="/cart">
                <i class="fa fa-shopping-cart"></i>
                Cart
            </a>
        </div>
        </#if>

        <#if user??>
            <div class="navbar-text mr-3">
                <a class="nav-link " href="/order">
                    <i class="fa fa-list-ul"></i>
                    Orders
                </a>
            </div>
        </#if>
        <div class="navbar-text mr-3">
            <#if user??>${name}<#else>Please, login</#if>
        </div>
        <@l.logout />
    </div>
</nav>
