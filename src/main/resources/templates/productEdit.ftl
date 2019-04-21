<#import "parts/common.ftl" as c>

<@c.page>
    Product editor

    <form action="/product" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">ProductName</label>
            <div class="col-sm-10">
                <h3>${product.productName}</h3>
            </div>
        </div>
        <fieldset class="form-group">
            <div class="row">
                <legend class="col-form-label col-sm-2 pt-0">Category</legend>
                <div class="col-sm-10">
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="category" id="category1"
                               value="FIRST_CATEGORY" <#if product.category == "FIRST_CATEGORY">checked="checked" </#if>/>
                        <label class="form-check-label" for="category1">
                            FIRST_CATEGORY
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="category" id="category2"
                               value="SECOND_CATEGORY" <#if product.category == "SECOND_CATEGORY">checked="checked" </#if>/>
                        <label class="form-check-label" for="category2">
                            SECOND_CATEGORY
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="category" id="category3"
                               value="THIRD_CATEGORY" <#if product.category == "THIRD_CATEGORY">checked="checked" </#if>/>
                        <label class="form-check-label" for="category3">
                            THIRD_CATEGORY
                        </label>
                    </div>
                </div>
            </div>
        </fieldset>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Price</label>
            <div class="col-sm-10" id="price">
                <input type="number" step="0.01" min="0.01" class="form-control" name="price"
                       value="<#setting locale = "en_US">${product.price?string("##0.00")}" required="required"/>
            </div>
        </div>
        <div class="form-group row" >
            <label class="col-sm-2 col-form-label">Width</label>
            <div class="col-sm-10" id="width">
                <input type="number" step="1" min="1" max="2147483647" class="form-control"
                       name="width" value="${product.width}" required="required"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Depth</label>
            <div class="col-sm-10">
                <input type="number" step="1" min="1" max="2147483647" class="form-control"
                       name="depth" value="${product.depth}" required="required"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Height</label>
            <div class="col-sm-10">
                <input type="number" step="1" min="1" max="2147483647" class="form-control"
                       name="height" value="${product.height}" required="required"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Weight</label>
            <div class="col-sm-10">
                <input type="number" step="1" min="1" max="2147483647" class="form-control"
                       name="weight" value="${product.weight}" required="required"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">QuantityOnPallet</label>
            <div class="col-sm-10">
                <input type="number" step="1" min="1" max="2147483647" class="form-control"
                       name="quantityOnPallet" value="${product.quantityOnPallet}" required="required"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">QuantityInWarehouse</label>
            <div class="col-sm-10">
                <input type="number" step="1" min="0" max="2147483647" class="form-control"
                       name="quantityInWarehouse" value="${product.quantityInWarehouse}" required="required"/>
            </div>
        </div>
        <div class="form-check">
            <input type="checkbox" class="form-check-input" id="exampleCheck1"
                   name="currentVersion" <#if product.currentVersion==true>checked="checked"</#if> />
            <label class="form-check-label" for="exampleCheck1">Current version</label>
        </div>

        <input type="hidden" value="${product.id}" name="id"/>
        <input type="hidden" value="${product.productName}" name="productName"/>
        <input type="hidden" value="${_csrf.token}" name="_csrf"/>
        <div class="form-group row">
            <div class="col-sm-10">
                <button type="submit" class="btn btn-primary">Save</button>
            </div>
        </div>
    </form>
</@c.page>
