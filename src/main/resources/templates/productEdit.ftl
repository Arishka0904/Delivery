<#import "parts/common.ftl" as c>

<@c.page>
    Product editor

    <form action="/product" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">ProductName</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="productName" value="${product.productName}"/>
            </div>
        </div>

        <fieldset class="form-group">
            <div class="row">
                <legend class="col-form-label col-sm-2 pt-0">Category</legend>
                <div class="col-sm-10">
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="category" id="category1"
                               value="FIRST_CATEGORY" <#if product.category == "FIRST_CATEGORY">checked</#if>/>
                        <label class="form-check-label" for="category1">
                            FIRST_CATEGORY
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="category" id="category2"
                               value="SECOND_CATEGORY" <#if product.category == "SECOND_CATEGORY">checked</#if>/>
                        <label class="form-check-label" for="category2">
                            SECOND_CATEGORY
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="category" id="category3"
                               value="THIRD_CATEGORY" <#if product.category == "THIRD_CATEGORY">checked</#if>/>
                        <label class="form-check-label" for="category3">
                            THIRD_CATEGORY
                        </label>
                    </div>
                </div>
            </div>
        </fieldset>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Price</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="price" value="${product.price}"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Width</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="width" value="${product.width}"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Depth</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="depth" value="${product.depth}"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Height</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="height" value="${product.height}"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Weight</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="weight" value="${product.weight}"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">QuantityOnPallet</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="quantityOnPallet" value="${product.quantityOnPallet}"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">QuantityInWarehouse</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="quantityInWarehouse"
                       value="${product.quantityInWarehouse}"/>
            </div>
        </div>

        <input type="hidden" value="${product.id}" name="productId"/>
        <input type="hidden" value="${_csrf.token}" name="_csrf"/>
        <div class="form-group row">
            <div class="col-sm-10">
                <button type="submit" class="btn btn-primary">Save</button>
            </div>
        </div>
    </form>
</@c.page>