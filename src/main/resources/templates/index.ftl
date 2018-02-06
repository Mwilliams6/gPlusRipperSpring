<#import "layout/layout.ftl" as Layout/>
<context:property-placeholder location="classpath:/application.properties" />

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
                <input name="url" class="form-control input-sm" value="www.google.com"/>
            </div>
        </div>

        <div class="form-group">
            <label for="" class="col-sm-3 control-label"></label>
            <div class="col-sm-6">
                <input type="submit" class="btn btn-info" value="Go!"/>
            </div>
        </div>

    </form>
</div>


<script src="${rc.contextPath}/js/upload.js"></script>
</@Layout.mainLayout>

