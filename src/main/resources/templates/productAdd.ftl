<#import "parts/common.ftl" as c>

<@c.page>
    New Product

    <form action="/product/add" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">ProductName</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="productName" placeholder="Enter productName"/>
            </div>
        </div>
        <fieldset class="form-group">
            <div class="row">
                <legend class="col-form-label col-sm-2 pt-0">Category</legend>
                <div class="col-sm-10">
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="category" id="category1" value="FIRST_CATEGORY"/>
                        <label class="form-check-label" for="category1">
                            FIRST_CATEGORY
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="category" id="category2" value="SECOND_CATEGORY"/>
                        <label class="form-check-label" for="category2">
                            SECOND_CATEGORY
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="category" id="category3" value="THIRD_CATEGORY"/>
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
                <input type="text" class="form-control" name="price" placeholder="Enter price"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Width</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="width" placeholder="Enter width"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Depth</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="depth" placeholder="Enter depth"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Height</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="height" placeholder="Enter height"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Weight</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="weight" placeholder="Enter weight"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">QuantityOnPallet</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="quantityOnPallet" placeholder="Enter Quantity On Pallet"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">QuantityInWarehouse</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="quantityInWarehouse" placeholder="Enter Quantity In Warehouse""/>
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