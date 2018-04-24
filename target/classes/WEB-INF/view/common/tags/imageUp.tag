@/*
头像参数的说明:
name : 名称
id : 图片的id
@*/
<div class="form-group">
    <label class="col-sm-2 control-label head-scu-label">${name}</label>
    <div class="col-sm-8">
        <div id="${id}PreId">
            <div id="${id}img">
            @if(isEmpty(avatarImg)){
                <img width="300px" height="100px" alt="noImage" src="${ctxPath}/static/img/upImage.png">
            @}else{
                <img width="100px" height="100px" src="src="${ctxPath}/kaptcha/${avatarImg}">
            @}
            </div>
        </div>
    </div>
    <div class="col-sm-2">
        <div class=" upload-btn" id="${id}BtnId" >
            <i class="fa fa-upload"></i>&nbsp;上传<br>
        </div>

        <div class=" upload-btn webuploader-container" id="${id}dump" onclick="dumpAll('${id}');">
            <div class="webuploader-pick" style="background-color: #999;">
                <i class="glyphicon glyphicon-trash"></i>&nbsp;清除
            </div>
            <div id="${id}dumpBtn" style="position: absolute; top: 0px; left: 0px; width: 119px; height: 32px; overflow: hidden; bottom: auto; right: auto;">
                <label style="opacity: 0; width: 100%; height: 100%; display: block; cursor: pointer; background: rgb(255, 255, 255);"></label>
            </div>
        </div>


    </div>
    <input type="hidden" id="${id}" value="${avatarImg!}"/>
</div>
@if(isNotEmpty(underline) && underline == 'true'){
    <div class="hr-line-dashed"></div>
@}
<script>
    function dumpAll(id) {
        console.log($("#"+id+"PreId div img"));
        var alt = $("#"+id+"PreId div img").attr("alt");
        console.log(alt);
        if(alt == "noImage"){
            layer.alert("无图片！");
        }else{
            $("#"+id+"PreId div img").attr("src","${ctxPath}/static/img/upImage.png");
            $("#"+id+"PreId div img").attr("width","300px");
        }
    }
</script>