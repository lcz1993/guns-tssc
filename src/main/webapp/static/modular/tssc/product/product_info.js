/**
 * 初始化产品详情对话框
 */
var ProductInfoDlg = {
    productInfoData : {}
};

/**
 * 清除数据
 */
ProductInfoDlg.clearData = function() {
    this.productInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ProductInfoDlg.set = function(key, val) {
    this.productInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ProductInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ProductInfoDlg.close = function() {
    parent.layer.close(window.parent.Product.layerIndex);
}

/**
 * 收集数据
 */
ProductInfoDlg.collectData = function() {
    var body = um.getContent();
    this.productInfoData.introduce = body;
    var genre = '';
    var service = $("input[name='genreId']:checked").each(function(j) {
        if (j >= 0) {
            genre += $(this).val() + ","
        }
    });
    this.productInfoData.genreId = genre;
    var options = $("#teamId option:selected").val();
    this.productInfoData.teamId = options;
    var image = $("#image1").val() +","+ $("#image2").val() +","+ $("#image3").val();
    this.productInfoData.image = image;

    this.set('id').set("name").set("year").set("boundary").set("customer");
}

/**
 * 提交添加
 */
ProductInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/product/add", function(data){
        Feng.success("添加成功!");
        window.parent.location.reload();
        ProductInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.productInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ProductInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/product/update", function(data){
        Feng.success("修改成功!");
        window.parent.location.reload();
        ProductInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.productInfoData);
    ajax.start();
}

$(function() {
    // 初始化图片上传
    var url = '/product/upload'

    var boundary = new $WebUpload("boundary",url,false);
    boundary.init();

    var image1 = new $WebUpload("image1",url,true);
    image1.init();
    var image2 = new $WebUpload("image2",url,true);
    image2.init();
    var image3 = new $WebUpload("image3",url,true);
    image3.init();
});
