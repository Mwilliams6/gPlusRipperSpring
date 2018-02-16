<#import "layout/layout.ftl" as Layout/>
<context:property-placeholder location="classpath:/application.properties" />
<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>

<script type="text/javascript">
    function madeAjaxCall(){
        $.ajax({
            type: "post",
            url: "http://localhost:8088/ripper/parseUrl",
            cache: false,
            data:'urlPath=' + $("#urlPath").val(),
            success: function(response){
                $('#subViewDiv').html("nothing");
                var obj = JSON.parse(response);
                $('#subViewDiv').html("First Name:- " + obj.toString());


            },
            error: function(){
                alert('Error while request..xxx');
            }
        });
    }
</script>

<@Layout.mainLayout>

<div class="block">
    <div class="block-title">
        <h2>Google Plus Image Ripper</h2>
    </div>
    <div class="urlForm">

        <form name="urlForm" method="post">



        <label for="prefix" class="col-sm-3 control-label">GPlus URL</label>

                <input id="urlPath" name="url" class="form-control input-sm" value="https://get.google.com/u/0/albumarchive/116749500979671626219"/>


            <label for="" class="col-sm-3 control-label"></label>
                <input type="submit" value="Go!" onclick="madeAjaxCall();"/>
       </form>
    </div>


</div>


<div id="subViewDiv">

</div>

</@Layout.mainLayout>

