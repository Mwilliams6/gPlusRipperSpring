<#import "layout/layout.ftl" as Layout/>
<context:property-placeholder location="classpath:/application.properties" />
<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>

<script type="text/javascript">

    $(document).ready(function () {

        $("#search-form").submit(function (event) {

            //stop submit the form, we will post it manually.
            event.preventDefault();

            madeAjaxCall();

        });

    });


    function madeAjaxCall(){
        $("#btn-search").prop("disabled", true);

        $.ajax({
            type: "post",
            url: "/ripper/parseUrl",
            dataType: 'json',
            cache: false,
            timeout: 600000,
            data:'urlPath=' + $("#urlPath").val(),
            success: function (data) {

                var json = "<h4>Ajax Response</h4>" + data;

                $('#feedback').html(json);

                console.log("SUCCESS : ", data);
                $("#btn-search").prop("disabled", false);

            },
            error: function (e) {

                var json = "<h4>Ajax Response</h4>"
                        + e.responseText;
                $('#feedback').html(json);

                console.log("ERROR : ", e);
                $("#btn-search").prop("disabled", false);

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

        <form class="form-horizontal" id="search-form">

          <label for="prefix" class="col-sm-3 control-label">GPlus URL</label>

          <input id="urlPath" name="url" class="form-control input-sm" value="https://get.google.com/u/0/albumarchive/116749500979671626219"/>
          <label for="" class="col-sm-3 control-label"></label>
          <button type="submit" id="bth-search"
                    class="btn btn-primary btn-lg">Search
          </button>
       </form>
    </div>


</div>


<div id="feedback" style="width:80%; margin: 0 auto;">


</div>

</@Layout.mainLayout>

