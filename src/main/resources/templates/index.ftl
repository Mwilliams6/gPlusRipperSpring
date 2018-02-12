<#import "layout/layout.ftl" as Layout/>
<context:property-placeholder location="classpath:/application.properties" />
<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>

<script type="text/javascript">
    function doAjaxPost() {

        $.ajax({
            type: "GET",
            url: "subView",
            success: function(response) {
                $("#subViewDiv").html( response );
            }
        });
    }
</script>

<@Layout.mainLayout>

<div class="block">
    <div class="block-title">
        <h2>Google Plus Image Ripper</h2>
    </div>
    <form action="${rc.contextPath}/parseUrl" method="POST" class="form-horizontal form-bordered"
          enctype="multipart/form-data">


        <div class="form-group">
            <label for="prefix" class="col-sm-3 control-label">GPlus URL</label>
            <div class="col-sm-6">
                <input name="url" class="form-control input-sm" value="https://get.google.com/u/0/albumarchive/116749500979671626219"/>
            </div>
        </div>

        <div class="form-group">
            <label for="" class="col-sm-3 control-label"></label>
            <div class="col-sm-6">
                <input type="submit" class="btn btn-info" value="Go!" onclick="doAjaxPost();"/>
            </div>
        </div>

    </form>
</div>


<script src="${rc.contextPath}/js/upload.js"></script>

<div id="subViewDiv"></div>

</@Layout.mainLayout>

