<#import "parts/common.ftl" as c>

<@c.page>
    New Product
    <form action="/product/add" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">ProductName</label>
            <div class="col-sm-10">
                <input type="text" class="form-control ${(productNameError??)?string('is-invalid', '')}"
                       name="productName" placeholder="Enter productName"
                       value="<#if product??>${product.productName}</#if>" required/>
                <#if productNameError??>
                    <div class="invalid-feedback">
                        ${productNameError}
                    </div>
                </#if>
            </div>
        </div>

        <fieldset class="form-group">
            <div class="row">
                <legend class="col-form-label col-sm-2 pt-0">Category</legend>
                <div class="col-sm-10">
                    <#if categoryError??>
                        <div class="alert alert-danger" role="alert">
                            ${categoryError}
                        </div>
                    </#if>
                    <div class="form-check ">
                        <input class="form-check-input ${(categoryError??)?string('is-invalid', '')}" type="radio"
                               name="category" id="category1"
                               value="FIRST_CATEGORY"/>
                        <label class="form-check-label" for="category1">
                            FIRST_CATEGORY
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input ${(categoryError??)?string('is-invalid', '')}" type="radio"
                               name="category" id="category2"
                               value="SECOND_CATEGORY"/>
                        <label class="form-check-label" for="category2">
                            SECOND_CATEGORY
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input ${(categoryError??)?string('is-invalid', '')}" type="radio"
                               name="category" id="category3"
                               value="THIRD_CATEGORY"/>
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
                <input type="number" step="0.01" min="0.01" required
                       class="form-control ${(priceError??)?string('is-invalid', '')}" name="price"
                       placeholder="Enter price" value="<#if product??>${product.price}</#if>" required/>
                <#if priceError??>
                    <div class="invalid-feedback">
                        ${priceError}
                    </div>
                </#if>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Width</label>
            <div class="col-sm-10">
                <input type="number" step="1" min="1" required
                       class="form-control ${(widthError??)?string('is-invalid', '')}" name="width"
                       placeholder="Enter width" value="<#if product??>${product.width}</#if>" required/>
                <#if widthError??>
                    <div class="invalid-feedback">
                        ${widthError}
                    </div>
                </#if>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Depth</label>
            <div class="col-sm-10">
                <input type="number" step="1" min="1" required
                       class="form-control ${(depthError??)?string('is-invalid', '')}" name="depth"
                       placeholder="Enter depth" value="<#if product??>${product.depth}</#if>" required/>
                <#if depthError??>
                    <div class="invalid-feedback">
                        ${depthError}
                    </div>
                </#if>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Height</label>
            <div class="col-sm-10">
                <input type="number" step="1" min="1" required
                       class="form-control ${(heightError??)?string('is-invalid', '')}" name="height"
                       placeholder="Enter height" value="<#if product??>${product.height}</#if>" required/>
                <#if heightError??>
                    <div class="invalid-feedback">
                        ${heightError}
                    </div>
                </#if>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Weight</label>
            <div class="col-sm-10">
                <input type="number" step="1" min="1" required
                       class="form-control ${(weightError??)?string('is-invalid', '')}" name="weight"
                       placeholder="Enter weight" value="<#if product??>${product.weight}</#if>" required/>

                <#if weightError??>
                    <div class="invalid-feedback">
                        ${weightError}
                    </div>
                </#if>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">QuantityOnPallet</label>
            <div class="col-sm-10">
                <input type="number" step="1" min="1" required
                       class="form-control ${(quantityOnPalletError??)?string('is-invalid', '')}"
                       name="quantityOnPallet" placeholder="Enter Quantity On Pallet"
                       value="<#if product??>${product.quantityOnPallet}</#if>" required/>
                <#if quantityOnPalletError??>
                    <div class="invalid-feedback">
                        ${quantityOnPalletError}
                    </div>
                </#if>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">QuantityInWarehouse</label>
            <div class="col-sm-10">
                <input type="number" step="1" min="0" required
                       class="form-control ${(quantityInWarehouseError??)?string('is-invalid', '')}"
                       name="quantityInWarehouse" placeholder="Enter Quantity In Warehouse"
                       value="<#if product??>${product.quantityInWarehouse}</#if>" required/>
                <#if quantityInWarehouseError??>
                    <div class="invalid-feedback">
                        ${quantityInWarehouseError}
                    </div>
                </#if>
            </div>
        </div>


        <input type="hidden" value="${_csrf.token}" name="_csrf"/>
        <div class="form-group row">
            <div class="col-sm-10">
                <button type="submit" class="btn btn-primary">Save</button>
            </div>
        </div>
    </form>
</@c.page>